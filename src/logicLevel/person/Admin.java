/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import java.util.List;
import logicLevel.CarRent;

/**
 *
 * @author Laura
 */
public abstract class Admin extends Person {
    public Admin(){};
    public Admin(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    
    public abstract List<Person> getAllPerson();
    public abstract void deletePersonById(int id);
    public abstract Person getPersonByName(String Name);
    public abstract List<Person> getPersonsByRole(String role);
}
