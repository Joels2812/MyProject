/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author march
 */
public interface IProject {
    abstract public double actualCost();
    abstract public double getBalance();
    abstract public ArrayList<Task> listTasks();
    abstract public ArrayList<Event> getLog();
    abstract public IPerson getManager();
    abstract public String getCode();
    abstract public String getName();
    abstract public double getInitialCost();
    abstract public LocalDate getStartDate();
    abstract public double acumulatedIncome();
    abstract public void save() throws SQLException;
    abstract public void delete() throws SQLException;
}
