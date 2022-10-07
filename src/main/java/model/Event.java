/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author march
 */
public class Event {
    private LocalDateTime dateTime;
    private String personName;
    private String detail;

    public Event(String personName, String detail) {
        this.dateTime = LocalDateTime.now();
        this.personName = personName;
        this.detail = detail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetail() {
        return detail;
    }

    public String getPersonName() {
        return personName;
    }
}
