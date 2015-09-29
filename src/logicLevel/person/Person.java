/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.Storable;
import db.person.DbPerson;


/**
 *
 * @author Laura
 */
public abstract class Person implements Storable{
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    public Person(){};
    public Person(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getPersonId() {
        return id;
    }
    
    public String getPersonName() {
        return name;
    }
    
    public String getPersonEmail() {
        return email;
    }
    
    public String getPersonPassword() {
        return password;
    }
    
    public String getPersonRole() {
        return role;
    }
    
    public void setPersonId(int id) {
        this.id = id;
    }
    
    public void setPersonName(String name) {
        this.name = name;
    }
    
    public void setPersonEmail(String email) {
        this.email = email;
    }
    
    public void setPersonPassword(String password) {
        this.password = password;
    }
    
    public void setPersonRole(String role) {
        this.role = role;
    }
    
    public void register(String name, String email, String password, String role) {
        if(name != null &&  email != null && password != null &&  role != null &&
                password.length() > 4) {
            Person person = new DbPerson(-1, name, email, password, role);
            person.save();
        }
    }
    public abstract Person login(String email, String password);
}
