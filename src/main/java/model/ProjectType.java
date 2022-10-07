/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author march
 */
public enum ProjectType {
    TEMPORARY("Temporary"),PERMANET("Permanent");
    
    private final String name;

    ProjectType(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
}
