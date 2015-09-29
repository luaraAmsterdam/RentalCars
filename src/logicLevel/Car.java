/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import db.Storable;
import logicLevel.person.Owner;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public abstract class Car implements Storable{
    private int id;
    private String vin;
    private String mark;
    private String model;
    private float volume;
    private float power;
    private String color;
    private float cost;
    private Person owner;
    
    public Car(int id, String vin, String mark, String model, float volume, float power,
            String color, float cost, Person owner) {
        this.id  = id;
        this.vin = vin;
        this.mark = mark;
        this.model = model;
        this.volume = volume;
        this.power = power;
        this.color = color;
        this.cost = cost;
        this.owner = owner;
    }
    public void setCarId(int id) {
        this.id = id;
    }
    
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    
    public int getCarId() {
        return id;
    }
    
    public String getCarVin() {
        return vin;
    }
    
    public String getCarMark() {
        return mark;
    }
    
    public String getCarModel() {
        return model;
    }
    
    public float getCarVolume() {
        return volume;
    }
    
    public float getCarPower() {
        return power;
    }    
    
    public String getCarColor() {
        return color;
    }
    
    public float getCarCost() {
        return cost;
    }   
    
    public Person getCarOwner() {
        return owner;
    }    
    
}
