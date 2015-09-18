/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;
 
import db.DbCar;


/**
 *
 * @author Laura
 */
public abstract class Owner extends Person {

    public Owner(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    
    public abstract void createCar(String vin, String mark, String model, float volume, float power,
            String color, float cost);
    public abstract void deleteCar(DbCar car);
    public abstract void editCar(DbCar car);
}
