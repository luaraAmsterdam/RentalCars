/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DbInsurance;
import logicLevel.person.Insurer;

/**
 *
 * @author Laura
 */
public class DbInsurer extends Insurer {

    public DbInsurer(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void createInsurance(float cost, String type) {
        DbInsurance dbInsurance = new DbInsurance(-1, cost, type, this);
        dbInsurance.save();
    }

    @Override
    public void deleteInsurance(DbInsurance dbInsurance) {
        dbInsurance.remove();
    }

    @Override
    public void editInsurance(DbInsurance dbInsurance) {
        dbInsurance.update();
    }

    @Override
    public void save() {
        DbPerson.save(getPersonName(), getPersonEmail(), getPersonPassword(), getPersonRole().toString());
    }

    @Override
    public void remove() {
        DbPerson.remove(getPersonId());
    }

    @Override
    public void update() {
        DbPerson.update(getPersonName(), getPersonEmail(), getPersonPassword(), getPersonRole().toString());
    }
    
}
