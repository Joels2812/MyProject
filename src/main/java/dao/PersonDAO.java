/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import model.IPerson;
import model.Person;

/**
 *
 * @author march
 */
public class PersonDAO extends MySqlConfig{
    public static void insertPerson(Person person) throws SQLException{
        try {
            getConnection();
            String sql = "{CALL insert_person(?,?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt("p_id", person.getId());
            cs.setString("p_surname", person.getSurname());
            cs.setString("p_lastname", person.getLastname());
            cs.setString("p_email",person.getEmail());
            cs.setInt("p_phone_number", person.getPhoneNumber());
            cs.setDate("p_borndate", Date.valueOf(person.getBorndate()));
            cs.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection();
        }
    }
    
    public static void deletePersonById(int id) throws SQLException{
        try {
            getConnection();
            String sql = "{CALL delete_person_id(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt("p_id", id);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection();
        }
    }
    
    public static ArrayList<IPerson> getPersons() throws SQLException {
        ArrayList<IPerson> persons = new ArrayList<>();
        getConnection();
        String sql = "{CALL get_all_persons}";
        CallableStatement cs = con.prepareCall(sql);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            IPerson currentPerson = new Person(rs.getInt("id"), rs.getString("surname"), rs.getString("lastname"),rs.getString("email"), rs.getInt("phone_number"), rs.getDate("borndate").toLocalDate());
            persons.add(currentPerson);
        }
        closeConnection();
        return persons;
    }
    
    public static IPerson getPersonById(int id)throws SQLException{
        IPerson person = null;
        getConnection();
        String sql = "{CALL get_person_by_id(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt("id", id);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            IPerson currentPerson = new Person(rs.getInt("id"), rs.getString("surname"), rs.getString("lastname"),rs.getString("email"), rs.getInt("phone_number"), rs.getDate("borndate").toLocalDate());
            person = currentPerson;
        }
        closeConnection();
        return person;
    }
    
}
