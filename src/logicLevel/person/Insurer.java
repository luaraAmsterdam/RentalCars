/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.DbInsurance;

/**
 *
 * @author Laura
 */
public abstract class Insurer extends Person{

    public Insurer(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
   
    public abstract void createInsurance(float cost, String type);
    public abstract void deleteInsurance(DbInsurance di);
    public abstract void editInsurance(DbInsurance di);
}
