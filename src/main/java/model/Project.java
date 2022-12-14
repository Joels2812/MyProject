package model;

import dao.ProjectDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author march
 */
public abstract class Project implements IProject,Comparable<Project>{
    private String code;
    private String name;
    private double initialCost;
    private LocalDate startDate;
    private ArrayList<Task> tasks;
    private ArrayList<Event> log;
    private IPerson manager;
    private ArrayList<IPerson> teamMembers;
    private double acumulatedIncome;

    public Project(String code, String name, double initialCost, LocalDate startDate, double acumulatedIncome) {
        this.code = code;
        this.name = name;
        this.initialCost = initialCost;
        this.startDate = startDate;
        this.tasks = new ArrayList<>();
        this.log = new ArrayList<>();
        this.manager = null;
        this.teamMembers = new ArrayList<>();
        this.acumulatedIncome = acumulatedIncome;
    }
    
    public Project(String name, double initialCost) {
        this.code = "";
        this.name = name;
        this.initialCost = initialCost;
        this.startDate = LocalDate.now();
        this.tasks = new ArrayList<Task>();
        this.log = new ArrayList<Event>();
        this.teamMembers = new ArrayList<IPerson>();
        this.manager = null;
        acumulatedIncome=0;
    }
    public Project(String name, double initialCost,int manager) {
        this.code = "";
        this.name = name;
        this.initialCost = initialCost;
        this.startDate = LocalDate.now();
        this.tasks = new ArrayList<Task>();
        this.log = new ArrayList<Event>();
        this.teamMembers = new ArrayList<IPerson>();
        this.manager = new Person(manager);
        acumulatedIncome=0;
    }

    @Override
    public double getBalance() {
        return acumulatedIncome - actualCost();
    }

    @Override
    public double getInitialCost() {
        return this.initialCost;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public ArrayList<Event> getLog() {
        return this.log;
    }

    @Override
    public IPerson getManager() {
        return this.manager;
    }

    @Override
    public LocalDate getStartDate() {
        return this.startDate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Task> listTasks() {
        return this.tasks;
    }
    
    public void setManager(IPerson manager){
        this.manager = manager;
    }
    
    @Override
    public boolean addTeamMember(IPerson member){
        if(!existMember(member)){
            teamMembers.add(member);
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Project o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public double acumulatedIncome() {
        return this.acumulatedIncome;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAcumulatedIncome(double acumulatedIncome) {
        this.acumulatedIncome = acumulatedIncome;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
   
    
    private boolean existMember(IPerson member){
        return teamMembers.stream().filter(registeredMember -> registeredMember.getId()==member.getId()).findFirst().isPresent();
    }
    public static ArrayList<IProject> getAllProjects() throws SQLException{
        ArrayList<IProject> projects = new ArrayList<>();
        projects = ProjectDAO.getAllProjects();
        return projects;
    }

    @Override
    public void delete() throws SQLException {
        ProjectDAO.deleteProjectByCode(code);
    }
    @Override
    public ArrayList<IPerson> getTeamMembers() {
        return teamMembers;
    }
    
    
    
    
    
}
