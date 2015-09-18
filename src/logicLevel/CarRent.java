/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import db.Storable;

/**
 *
 * @author Laura
 */
public abstract class CarRent  implements Storable {
    private int id;
    private Car car;
    private Insurance insurance;
    private String dateFrom;
    private String dateTo;
    private float resultCost;
    
    public CarRent(int id, Car car, Insurance insurance, String dateFrom,
            String dateTo, float resultCost) {
        this.id = id;
        this.car = car;
        this.insurance = insurance;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.resultCost = resultCost;
    }
    
    public void setCarRentId(int id) {
        this.id = id;
    }
    
    public void setCar(Car car) {
        this.car = car;
    } 
    
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
    
    public int getCarRentId() {
        return id;
    }
    
    public Car getCarRentCar() {
        return car;
    }
    
    public Insurance getCarRentInsurance() {
        return insurance;
    }
    
    public String getCarRentDateFrom() {
        return dateFrom;
    }
    
    public String getCarRentDateTo() {
        return dateTo;
    }
    
    public float getCarRentResultCost() {
        return resultCost;
    }
    
}
