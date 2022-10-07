/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author march
 */
public interface IPerson {
    public abstract int getId();
    public abstract int getPhoneNumber();
    public abstract String getEmail();
    public abstract String getSurname();
    public abstract String getLastname();
    public abstract LocalDate getBorndate();
    public abstract void addProject(IProject project);
    public abstract String getFullname();
    public abstract void save() throws SQLException;
    public abstract void delete() throws SQLException;
}
