/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ProjectDAO;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author march
 */
public class Temporary extends Project{
    private LocalDate finishDate;
    private String country;
    private double dolarExchange;

    public Temporary(LocalDate finishDate, String country,String name, double initialCost) {
        super(name, initialCost);
        this.finishDate = finishDate;
        this.country = country;
        this.dolarExchange = 1;
    }
    public Temporary(LocalDate finishDate, String country,String name, double initialCost,int manager) {
        super(name, initialCost,manager);
        this.finishDate = finishDate;
        this.country = country;
        this.dolarExchange = 1;
    }
    
    
    public Temporary(LocalDate finishDate, String country,String name, double initialCost,double dolarExchange,int manager) {
        super(name, initialCost,manager);
        this.finishDate = finishDate;
        this.country = country;
        this.dolarExchange = dolarExchange;
    }
    @Override
    public double actualCost() {
        return super.getInitialCost()*dolarExchange;
    }

    @Override
    public void save() throws SQLException {
        ProjectDAO.insert_temporary_project(this, finishDate, country, dolarExchange);
    }
    
    public static IProject getProjectByCode(String code) throws SQLException{
            return ProjectDAO.getTemporaryProjectByCode(code);
    }
}
