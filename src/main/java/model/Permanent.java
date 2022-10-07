/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author march
 */
public class Permanent extends Project{
    private double fixedAnnualCost;
    private double percentageInflation;

    public Permanent(double fixedAnnualCost, double percentageInflation, String name, double initialCost) {
        super(name, initialCost);
        this.fixedAnnualCost = fixedAnnualCost;
        this.percentageInflation = percentageInflation;
    }

    @Override
    public double actualCost() {
        return (super.getInitialCost()+fixedAnnualCost) + (fixedAnnualCost*percentageInflation);
    }
}
