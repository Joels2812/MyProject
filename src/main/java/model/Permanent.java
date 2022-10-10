package model;

import dao.ProjectDAO;
import java.sql.SQLException;

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
    public Permanent(double fixedAnnualCost, double percentageInflation, String name, double initialCost,int manager) {
        super(name, initialCost,manager);
        this.fixedAnnualCost = fixedAnnualCost;
        this.percentageInflation = percentageInflation;
    }

    @Override
    public double actualCost() {
        return (super.getInitialCost()+fixedAnnualCost) + (fixedAnnualCost*percentageInflation);
    }

    @Override
    public void save() throws SQLException {
         ProjectDAO.insert_permanent_project(this, fixedAnnualCost, percentageInflation);
    }
    
    public static IProject getProjectByCode(String code) throws SQLException{
            return ProjectDAO.getPermanentProjectByCode(code);
    }

    @Override
    public ProjectType type() {
        return ProjectType.PERMANET;
    }
    
}