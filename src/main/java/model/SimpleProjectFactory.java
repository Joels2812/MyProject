/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author march
 */
public class SimpleProjectFactory {
    public static IProject createProject(ProjectType type) throws ClassNotFoundException,IllegalAccessException, InstantiationException{
        IProject newProject = null;
        newProject = (IProject)Class.forName(type.getName()).newInstance();
        return newProject;
    }
}
