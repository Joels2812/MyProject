/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import java.time.LocalDate;
import model.IPerson;
import model.Person;

/**
 *
 * @author march
 */
public class Hola {
    public static void main(String[] args) {
        try {
            IPerson ps = new Person(123, "Hola", "test", "marchena.gdasd", 456134534, LocalDate.now());
            ps.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
