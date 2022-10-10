/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import dao.ProjectDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Hola {
    public static void main(String[] args) {
        try {
            IProject newProject = new Temporary(LocalDate.of(2000, 1, 1), "United States", "My gringro project", 25000, 620,117960697);
            //IProject newProject = Temporary.getProjectByCode("PRY-0006");
            ArrayList<IProject> projects = ProjectDAO.getAllProjects();
            System.out.println(projects.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
