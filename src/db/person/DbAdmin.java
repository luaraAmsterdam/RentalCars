/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DB;
import db.DbCar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.person.Admin;

/**
 *
 * @author Laura
 */
public class DbAdmin extends Admin{

    public DbAdmin(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void deletePerson(int id) {
        deleteObject("Person", id);
    }

    @Override
    public void deleteCar(int id) {
        deleteObject("Car", id);
    }

    @Override
    public void deleteInsurance(int id) {
        deleteObject("Insurance", id);
    }

    @Override
    public void deleteCarRent(int id) {
        deleteObject("Car_rent", id);
    }

    @Override
    public void save() {
        DbPerson.save(getPersonName(), getPersonEmail(), getPersonPassword(), getPersonRole());
    }

    @Override
    public void remove() {
        DbPerson.remove(getPersonId());
    }

    @Override
    public void update() {
        DbPerson.update(getPersonName(), getPersonEmail(), getPersonPassword(), getPersonRole());
    }
    
    private void deleteObject(String table, int id) {
        String str = "delete from " + table + " where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
