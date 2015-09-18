/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.Storable;

/**
 *
 * @author Laura
 */
public abstract class Person implements Storable {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    
    public Person(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
//    public void setPersonId(int id) {
//        this.id = id;
//    }
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
    
    
//    public static enum Role {Admin, Owner, Renter, Insurer};
}
