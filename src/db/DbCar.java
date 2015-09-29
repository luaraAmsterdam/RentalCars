/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.person.DbOwner;
import db.person.DbPerson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Car;
import logicLevel.person.Owner;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbCar extends Car {
    public DbCar(int id, String vin, String mark, String model, float volume, float power,
            String color, float cost, Person owner) {
        super(id, vin, mark, model, volume, power, color, cost, owner);
    }
    

    @Override
    public void save() {
        String str = "INSERT INTO Car (vin, mark, model, volume, powerc, color, "
                + "cost, id_owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        updateOrInsert(str);
        DB.commit();
    }

    @Override
    public void update() {
        String str = "update Car set vin=?, mark=?, model=?, volume=?, powerc=?, color=?, "
                + "cost=?, id_owner=?";
        DB.commit();
    }
    
    @Override
    public void remove() {
        String str = "delete from car where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, getCarId());
	    ps.execute();
            DB.commit();
	} catch (SQLException ex) {
	    Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static Car parse(ResultSet set) throws SQLException {
        int owner_id = set.getInt("id_owner");
        
        Car car = new DbCar(set.getInt("id"), set.getString("vin"), set.getString("mark"), set.getString("model"),
                set.getFloat("volume"), set.getFloat("powerc"), set.getString("color"), set.getFloat("cost"),
                null);
        Person owner = getPersonById(owner_id);
        car.setOwner(owner);
        return car;
    }
    
    private void updateOrInsert(String str) {
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            Person owner = getCarOwner();
            ps.setString(1, getCarVin());
            ps.setString(2, getCarMark());
            ps.setString(3, getCarModel());
            ps.setFloat(4, getCarVolume());
            ps.setFloat(5, getCarPower());
            ps.setString(6, getCarColor());
            ps.setFloat(7, getCarCost());
            ps.setInt(8, owner.getPersonId());
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) 
                setCarId(rs.getInt(1));     
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Person getPersonById(int id) {
        String query = "SELECT * FROM Person WHERE ID = " + id;
        return getPersonList(query).get(0);
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
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}
