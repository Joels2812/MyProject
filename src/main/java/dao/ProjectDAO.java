/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import static dao.MySqlConfig.closeConnection;
import static dao.MySqlConfig.con;
import static dao.MySqlConfig.getConnection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.IPerson;
import model.IProject;
import model.Permanent;
import model.Person;
import model.Project;
import model.Temporary;
/**
 *
 * @author march
 */
public class ProjectDAO extends MySqlConfig{
    public static void insert_permanent_project(Project newProject,double fixed_annual_cost,double percentageInflation) throws SQLException {
        try {
            getConnection();
            String sql= "{CALL insert_permanent_project(?,?,?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString("p_pro_name", newProject.getName());
            cs.setDouble("p_initial_cost", newProject.getInitialCost());
            cs.setDate("p_pro_start_date", Date.valueOf(newProject.getStartDate()));
            cs.setDouble("p_acumulated_income", newProject.acumulatedIncome());
            cs.setInt("p_manager_id", newProject.getManager().getId());
            cs.setDouble("p_fixed_anual_cost", fixed_annual_cost);
            cs.setDouble("p_inflation_percentage", percentageInflation);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        }finally{
            closeConnection();
        }
    }
    public static void insert_temporary_project(Project newProject,LocalDate finishDate, String country,double exchange) throws SQLException {
        try {
            getConnection();
            String sql= "{CALL insert_temporary_project(?,?,?,?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString("p_pro_name", newProject.getName());
            cs.setDouble("p_initial_cost", newProject.getInitialCost());
            cs.setDate("p_pro_start_date", Date.valueOf(newProject.getStartDate()));
            cs.setInt("p_manager_id", newProject.getManager().getId());
            cs.setDouble("p_acumulated_income", newProject.acumulatedIncome());
            cs.setDate("p_finish_date", Date.valueOf(finishDate));
            cs.setString("p_country", country);
            cs.setDouble("p_exchange", exchange);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        }finally{
            closeConnection();
        }
    }
    
    public static ArrayList<IProject> getAllPermanentProjects() throws SQLException{
        ArrayList<IProject> projects = new ArrayList<>();
        getConnection();
        String sql = "{CALL get_all_permanent_project()}";
        CallableStatement cs = con.prepareCall(sql);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            Project newProject = new Permanent(rs.getDouble("fixed_anual_cost"), rs.getDouble("inflation_percentage"), rs.getString("pro_name"), rs.getDouble("initial_cost"));
            newProject.setCode(rs.getString("code"));
            newProject.setAcumulatedIncome(rs.getDouble("acumulated_income"));
            newProject.setStartDate(rs.getDate("pro_start_date").toLocalDate());
            newProject.setManager(PersonDAO.getPersonById(rs.getInt("manager")));
            IProject iNewProject = newProject;
            projects.add(iNewProject);
            
        }
        closeConnection();
        return projects;
    }
    
    public static ArrayList<IProject> getAllTemporaryProjects() throws SQLException{
        ArrayList<IProject> projects = new ArrayList<>();
        getConnection();
        String sql = "{CALL get_all_temporary_project}";
        CallableStatement cs = con.prepareCall(sql);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            Project newProject = new Temporary(rs.getDate("finish_date").toLocalDate(), rs.getString("country"), rs.getString("pro_name"), rs.getDouble("initial_cost"));
            newProject.setCode(rs.getString("code"));
            newProject.setAcumulatedIncome(rs.getDouble("acumulated_income"));
            newProject.setStartDate(rs.getDate("pro_start_date").toLocalDate());
            newProject.setManager(PersonDAO.getPersonById(rs.getInt("manager")));
            IProject iNewProject = newProject;
            projects.add(iNewProject);
        }
        closeConnection();
        return projects;
    }
    
    public static ArrayList<IProject> getAllProjects() throws SQLException{
        ArrayList<IProject> projects = new ArrayList<>();
        projects.addAll(getAllPermanentProjects());
        projects.addAll(getAllTemporaryProjects());
        return projects;
    }
    
    public static IProject getTemporaryProjectByCode(String code) throws SQLException{
      getConnection();
        String sql = "{CALL get_temporary_project_by_code(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString("code", code);
        ResultSet rs = cs.executeQuery();
        IProject project = null;
        while(rs.next()){
            Project newProject = new Temporary(rs.getDate("finish_date").toLocalDate(), rs.getString("country"), rs.getString("pro_name"), rs.getDouble("initial_cost"));
            newProject.setCode(rs.getString("code"));
            newProject.setAcumulatedIncome(rs.getDouble("acumulated_income"));
            newProject.setStartDate(rs.getDate("pro_start_date").toLocalDate());
            newProject.setManager(PersonDAO.getPersonById(rs.getInt("manager")));
            project = newProject;
            
        }
        closeConnection();
        return project;  
    }
    
    public static IProject getPermanentProjectByCode(String code) throws SQLException{
        IProject project =null;
        getConnection();
        String sql = "{CALL get_permanent_project_by_code(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString("code", code);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            Project newProject = new Permanent(rs.getDouble("fixed_anual_cost"), rs.getDouble("inflation_percentage"), rs.getString("pro_name"), rs.getDouble("initial_cost"));
            newProject.setCode(rs.getString("code"));
            newProject.setAcumulatedIncome(rs.getDouble("acumulated_income"));
            newProject.setStartDate(rs.getDate("pro-start_date").toLocalDate());
            newProject.setManager(PersonDAO.getPersonById(rs.getInt("manager")));
            project = newProject;
            
        }
        closeConnection();
        return project;
    }
    
    public static void deleteProjectByCode(String code) throws SQLException{
        try {
            getConnection();
            String sql = "{CALL delete_project_by_code(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString("code", code);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        }finally{
            closeConnection();
        }
    }
    
    public static void addTeamMember(int id,String code) throws SQLException{
        try {
            getConnection();
            String sql = "{CALL add_team_member(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt("idMember", id);
            cs.setString("pro_code", code);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        }finally{
            closeConnection();
        }
    }
    
    public static ArrayList<IPerson> getTeamMembersByProject(IProject project) throws SQLException{
        ArrayList<IPerson> persons = new ArrayList<>();
        getConnection();
        String sql = "{CALL get_team_members_by_projectCode(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString("pro_code", project.getCode());
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            IPerson currentPerson = new Person(rs.getInt("id"), rs.getString("surname"), rs.getString("lastname"),rs.getString("email"), rs.getInt("phone_number"), rs.getDate("borndate").toLocalDate());
            project.addTeamMember(currentPerson);
        }
        closeConnection();
        return persons;
    }
    
}
