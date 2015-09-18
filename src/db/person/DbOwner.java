/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DbCar;
import logicLevel.Car;
import logicLevel.person.Owner;

/**
 *
 * @author Laura
 */
public class DbOwner extends Owner{

    public DbOwner(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void createCar(String vin, String mark, String model, float volume, float power,
            String color, float cost) {
        DbCar dbCar = new DbCar(-1, vin, mark, model, volume, power, color, cost, this);
    }

    @Override
    public void deleteCar(DbCar car) {
        car.save();
    }

    @Override
    public void editCar(DbCar car) {
        car.update();
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
