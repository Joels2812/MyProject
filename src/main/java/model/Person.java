package model;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.PersonDAO;
import java.sql.SQLException;
/**
 *
 * @author march
 */

public class Person implements IPerson,Comparable<Person>{
    private int id;
    private int phoneNumber;
    private String email;
    private String surname;
    private String lastname;
    private LocalDate bornDate;
    private ArrayList<IProject> myProjects;
    
    public Person(int id){
        this.id = id;
        this.surname = "";
        this.lastname = "";
        this.email = "";
        this.phoneNumber = 0;
        this.bornDate = null;
        this.myProjects = new ArrayList<>();
    }
    
    public Person(int id,String surname,String lastname,String email,int phoneNumber,String bornDate){
        this.id = id;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bornDate = parseStringToLocalDate(bornDate);
        this.myProjects = new ArrayList<>();
    }
    public Person(int id,String surname,String lastname,String email,int phoneNumber,LocalDate bornDate){
        this.id = id;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
        this.myProjects = new ArrayList<>();
    }
    @Override
    public int getId(){
        return this.id;
    }
    @Override
    public int getPhoneNumber(){
        return this.phoneNumber;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public LocalDate getBorndate() {
        return this.bornDate;
    }

    @Override
    public String getLastname() {
        return this.lastname;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getFullname() {
        return this.surname +" "+ this.lastname;
    }
    @Override
    public void addProject(IProject project) {
        this.myProjects.add(project);
    }

    @Override
    public int compareTo(Person per) {
        return this.getFullname().compareToIgnoreCase(per.getFullname());
    }
     
    private static LocalDate parseStringToLocalDate(String stringDate){
        LocalDate date = null;
        return date;
    }
    @Override
    public void save() throws SQLException{
        PersonDAO.insertPerson(this);
    }

    @Override
    public void delete() throws SQLException {
        PersonDAO.deletePersonById(this.id);
    }
    
    
    public static ArrayList<IPerson> getPersons() throws SQLException{
        return PersonDAO.getPersons();
    }
    
    public static IPerson getPersonById(int id) throws SQLException {
        return PersonDAO.getPersonById(id);
    }
    
}
