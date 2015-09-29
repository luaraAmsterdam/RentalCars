/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.person.Admin;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbAdmin extends Admin{
    public DbAdmin(){};
    public DbAdmin(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    
    @Override
    public void deletePersonById(int id) {
        DbPerson.remove(id);
        DB.commit();
    }

    @Override
    public Person getPersonByName(String name) {
        String query = "SELECT * FROM PERSON WHERE NAME = '" + name + "'";
        return getPersonList(query).get(0);
    }

    @Override
    public List<Person> getPersonsByRole(String role) {
        String query = "SELECT * FROM PERSON WHERE ROLE = '" + role + "'";
        return getPersonList(query);
    }
    
        @Override
    public List<Person> getAllPerson() {
        String query = "SELECT * FROM PERSON";
        return getPersonList(query);
    }

    private static List<Person> getPersonList(String query) {
        List<Person> persons = new ArrayList<Person>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                persons.add(DbPerson.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }

    @Override
    public Person login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
