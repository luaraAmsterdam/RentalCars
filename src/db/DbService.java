/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.person.DbPerson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;
import logicLevel.Service;
import logicLevel.person.Person;
import logicLevel.person.Renter;

/**
 *
 * @author Laura
 */
public class DbService extends Service {

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
    public void deletePersonById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public CarRent getCarRentById(int id) {
        String query = "SELECT * FROM CAR_RENT WHERE ID = " + id;
        return getCarRent(query).get(0);
    }

    @Override
    public CarRent addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) {
        CarRent carRent = null;
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date dateF = (Date) format.parse(from);
            Date dateT = (Date) format.parse(to);
            Car car = getCarById(id_car);
            Insurance ins = getInsuranceById(id_car);
            long diff = (dateT.getTime() - dateF.getTime());
            int day = (int) (diff/86400000 +1);
            float carCost = car.getCarCost();
            float cost = ins.getInsuranceCost();
            float resultCost = (carCost + cost) * day * 1.1f;
            carRent = new DbCarRent(-1, id_Renter, car, ins, from, to, resultCost);
            carRent.save();
        } catch (ParseException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carRent;
    }

    @Override
    public void register(String name, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person login(String email, String password) {
        String query = "SELECT * FROM PERSON WHERE MAIL = '" 
                    + email + "' AND PASSWORD='" + password + "'";
        return getPersonList(query).get(0);
    }

    @Override
    public List<Person> getAllPerson() {
        String query = "SELECT * FROM PERSON";
        return getPersonList(query);
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
    

    
    //Insurer
    @Override
    public List<Insurance> getAllInsuranceForInsurer(int id) {
        String query = "SELECT * FROM Insurance where id_insurer = " + id;
        return getInsurance(query);
    }

    @Override
    public List<Insurance> getInsuranceByTypeForInsurer(String type, int id) {
        String query = "SELECT * FROM Insurance where type = '" + type + "' and "
                + " id_insurer = " + id;
        return getInsurance(query);
    }

    @Override
    public List<Object> getHistoryForInsurance(int id) {
        String query = "SELECT Car_rent.date_from, Car_rent.date_to, Insurance.cost FROM Car_rent, Insurance"
                + " where id_insurance = " + id + " and insurance.id = " + id;
        return getHistory(query);
    }

    @Override
    public Insurance addNewInsurance(float cost, String type, int id) {
        Person insurer = getPersonById(id);
        Insurance ins = new DbInsurance(-1, cost, type, insurer);
        ins.save();
        return ins;
    }

    //Owner
    @Override
    public List<Car> getCarByMarkForOwner(String mark, String model, int id) {
        String query = "select * from car where mark = '" + mark + "' and model = '" + model +
                "' and id_owner = " + id;
        return getCars(query);
    }

    @Override
    public List<Car> getAllCarForOwner(int id) {
        String query = "select * from car where id_owner = " + id;
        return getCars(query);
    }

    @Override
    public List<Object> getHistoryForCar(int id) {
        String query = "SELECT Car_rent.date_from, Car_rent.date_to, Car.cost FROM Car_rent, Car"
                + " where car.id_owner = " + id + " and car.id = car_rent.id_car";
        return getHistory(query);
    }

    @Override
    public void deleteCarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car addNewCar(String mark, String model, float power, float volume, String color, float cost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public Person getPersonById(int id) {
        String query = "SELECT * FROM Person WHERE ID = " + id;
        return getPersonList(query).get(0);
    }

    public Car getCarById(int id) {
        String query = "SELECT * FROM Car WHERE ID = " + id;
        return getCars(query).get(0);
    }

    public Insurance getInsuranceById(int id) {
        String query = "SELECT * FROM Insurance WHERE ID = " + id;
        return getInsurance(query).get(0);
    }
    
    private List<Object> getHistory(String query) {
        List<Object> history = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String from = rs.getString("date_from");
                String to = rs.getString("date_to");
                float cost = rs.getFloat("cost");
                float resultCost = 0;
                try {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date from_d = df.parse(from);
                    Date to_d = df.parse(to);
                    long diff = (to_d.getTime() - from_d.getTime());
                    resultCost = (diff/86400000 + 1) * cost;
                } catch (ParseException ex) {
                    Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.add(new Object[]{from, to, resultCost});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
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
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
    
    private List<Car> getCars(String query) {
        List<Car> cars = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
                cars.add(DbCar.parse(rs));
        } catch (SQLException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
    }
    
    private List<Insurance> getInsurance(String query) {
        List<Insurance> insurances = new ArrayList<Insurance>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                insurances.add(DbInsurance.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insurances;
    }
}
