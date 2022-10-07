/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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

    @Override
    public double actualCost() {
        return super.getInitialCost()*dolarExchange;
    }
    
    
    
    
}
