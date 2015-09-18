/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

/**
 *
 * @author Laura
 */
public abstract class Admin extends Person {

    public Admin(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    
    public abstract void deletePerson(int id);
    public abstract void deleteCar(int id);
    public abstract void deleteInsurance(int id);
    public abstract void deleteCarRent(int id);
}
