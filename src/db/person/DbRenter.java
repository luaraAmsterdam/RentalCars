/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DB;
import db.DbCar;
import db.DbCarRent;
import db.DbInsurance;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;
import logicLevel.person.Person;
import logicLevel.person.Renter;

/**
 *
 * @author Laura
 */
public class DbRenter extends Renter{
    public DbRenter(){};
    public DbRenter(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    @Override
    public List<Car> getAllCar() {
        String query = "SELECT * FROM Car";
        return getCars(query);
    }

    @Override
    public List<Insurance> getAllInsurance() {
        String query = "SELECT * FROM Insurance";
        return getInsurance(query);
    }

    @Override
    public List<CarRent> getAllCarRentForUser(int id) {
        String query = "SELECT * FROM CAR_RENT WHERE ID_RENTER = " + id;
        return getCarRent(query);
    }

    @Override
    public CarRent getCarRentById(int id) {
        String query = "SELECT * FROM CAR_RENT WHERE ID = " + id;
        return getCarRent(query).get(0);
    }

    @Override
    public List<Car> getCarByModel(String mark, String model, String color) {
        String query = "SELECT * FROM Car WHERE mark = '" + mark 
                    + "' and model = '" + model + "' and color = '" + color + "'"; 
        return getCars(query);
    }
    
    @Override
    public List<Car> getCarByPower(float power, float volume) {
        String query = "SELECT * FROM Car WHERE powerc > " + power 
                    + " and volume > " + volume;
        return getCars(query);    
    }

    @Override
    public List<Car> getCarByCost(float maxCost) {
        String query = "SELECT * FROM Car WHERE cost < " + maxCost;
        return getCars(query);   
    }

    @Override
    public List<Insurance> getInsuranceByType(String type) {
        String query = "SELECT * FROM Insurance where type = '" + type + "'";
        List<Insurance> insurances = getInsurance(query);
        return insurances;
    }

    @Override
    public List<Insurance> getInsuranceByInsurer(String name) {
        String query = "SELECT Insurance.id, Insurance.cost, Insurance.type, Insurance.id_insurer"
                + " FROM Insurance, Person WHERE Person.name = '" + name +"' and Person.id = Insurance.id_insurer";
        List<Insurance> insurances = getInsurance(query);
        return insurances;
    }

    @Override
    public Person login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonById(int id) {
        String query = "SELECT * FROM Person WHERE ID = " + id;
        return getPersonList(query).get(0);
    }

    public static Car getCarById(int id) {
        String query = "SELECT * FROM Car WHERE ID = " + id;
        return getCars(query).get(0);
    }

    public static Insurance getInsuranceById(int id) {
        String query = "SELECT * FROM Insurance WHERE ID = " + id;
        return getInsurance(query).get(0);
    }
    
    private static List<Insurance> getInsurance(String query) {
        List<Insurance> insurances = new ArrayList<Insurance>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                insurances.add(DbInsurance.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insurances;
    }
   
    
    private List<CarRent> getCarRent(String query) {
                List<CarRent> carRents = new ArrayList<CarRent>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                carRents.add(DbCarRent.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carRents;
    }
        
    private List<Person> getPersonList(String query) {
        List<Person> persons = new ArrayList<Person>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                persons.add(DbPerson.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
    
    private static List<Car> getCars(String query) {
        List<Car> cars = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
                cars.add(DbCar.parse(rs));
        } catch (SQLException ex) {
            Logger.getLogger(DbRenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
    }

    @Override
    public void save() {
        
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
