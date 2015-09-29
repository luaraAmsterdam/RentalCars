/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.DbCarRent;
import db.person.DbRenter;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;

/**
 *
 * @author Laura
 */
public abstract class Renter extends Person {
    private ArrayList<CarRent> carRentList;
    public Renter(){};
    public Renter(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
        carRentList = new ArrayList<CarRent>();
    }
    
    public abstract List<Car> getAllCar();
    public abstract List<Insurance> getAllInsurance();
    public abstract List<CarRent> getAllCarRentForUser(int id);
    public abstract CarRent getCarRentById(int id);
    public abstract List<Car> getCarByModel(String mark, String model, String color);
    public abstract List<Car> getCarByPower(float power, float volume);
    public abstract List<Car> getCarByCost(float minCost);
    public abstract List<Insurance> getInsuranceByType(String type);
    public abstract List<Insurance> getInsuranceByInsurer(String name);
    public CarRent addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) {
        if(id_Renter > 0 && id_car > 0 && id_insurance > 0 && from != null && to != null) {
            CarRent carRent = null;
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            try {
                java.util.Date dateF = (java.util.Date) format.parse(from);
                java.util.Date dateT = (java.util.Date) format.parse(to);
                Car car = DbRenter.getCarById(id_car);
                Insurance ins = DbRenter.getInsuranceById(id_car);
                long diff = (dateT.getTime() - dateF.getTime());
                int day = (int) (diff/86400000 +1);
                float carCost = car.getCarCost();
                float cost = ins.getInsuranceCost();
                float resultCost = (carCost + cost) * day * 1.1f;
                carRent = new DbCarRent(-1, id_Renter, car, ins, from, to, resultCost, "unreviewed", "unreviewed");
                carRent.save();
            } catch (ParseException ex) {
                Logger.getLogger(DbRenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return carRent;
        }
        else 
            return null;
    }
    
}
