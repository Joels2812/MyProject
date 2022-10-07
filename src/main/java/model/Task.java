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
public class Task {
    private LocalDate startDate;
    private LocalDate finishDate;
    private String name;
    private String status;

    public Task(LocalDate startDate, LocalDate finishDate, String name, String status) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.name = name;
        this.status = status;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    
}
