/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import db.Storable;
import logicLevel.person.Insurer;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public abstract class Insurance implements Storable{
    private int id;
    private float cost;
    private String type;
    private Person insurer;
    
    public Insurance(int id, float cost, String type, Person insurer) {
        this.id = id;
        this.cost = cost;
        this.type = type;
        this.insurer = insurer;
    }
    
    public void setInsuranceId(int id) {
        this.id = id;
    }
    
    public void setInsurer(Person insurer) {
        this.insurer = insurer;
    }
    
    public int getInsuranceId() {
        return id;
    }
    
    public float getInsuranceCost() {
        return cost;
    }
    
    public String getInsuranceType() {
        return type;
    }
    
    public Person getInsuranceInsurer() {
        return insurer;
    }
}
