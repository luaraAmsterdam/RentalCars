/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DB;
import db.DbCar;
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
import logicLevel.person.Owner;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbOwner extends Owner{

    public DbOwner() {};
    
    public DbOwner(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
        List<Car> cars = getAllCarForOwner(id);
        super.setCarRent(cars);
    }
    
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
    public List<Object> getUnreviewedOrderForOwner(int id) {
        String query = "SELECT Car_rent.id, Car_rent.id_car, Car_rent.date_from, Car_rent.date_to, Car.cost FROM Car_rent, Car"
                + " where car.id_owner = " + id + " and car.id = car_rent.id_car and car_rent.owner_approve = 'unreviewed'";
        List<Object> history = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id_rent = rs.getInt("id");
                int id_car = rs.getInt("id_car");        
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
                    Logger.getLogger(DbOwner.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.add(new Object[]{id_rent, id_car, cost, from, to, resultCost});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }

    @Override
    public void deleteCarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    Logger.getLogger(DbOwner.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.add(new Object[]{from, to, resultCost});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }
    private List<Car> getCars(String query) {
        List<Car> cars = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
                cars.add(DbCar.parse(rs));
        } catch (SQLException ex) {
            Logger.getLogger(DbOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
    }

    @Override
    public Person login(String email, String password) {return null;};

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
