/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.DbCarRent;
import java.util.ArrayList;
import java.sql.Date;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;

/**
 *
 * @author Laura
 */
public abstract class Renter extends Person {
    private ArrayList<CarRent> carRentList;
    
    public Renter(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
        carRentList = new ArrayList<CarRent>();
    }
    
    public ArrayList<CarRent> getCarRentList() {
        return carRentList;
    }
    
    public abstract void rentCar(int idRenter, Car car, Insurance insurance, String dateFrom, String dateTo, float resultCost);
    public abstract void deleteRentCar(DbCarRent car);
}
