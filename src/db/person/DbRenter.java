/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DbCarRent;
import java.sql.Date;
import logicLevel.Car;
import logicLevel.Insurance;
import logicLevel.person.Renter;

/**
 *
 * @author Laura
 */
public class DbRenter extends Renter{

    public DbRenter(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void rentCar(int idRenter, Car car, Insurance insurance, String dateFrom, String dateTo, float resultCost) {
        DbCarRent carRent = new DbCarRent(-1, idRenter, car, insurance, dateFrom, dateTo, resultCost);
        carRent.save();
    }

    @Override
    public void deleteRentCar(DbCarRent carRent) {
        carRent.remove();
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
