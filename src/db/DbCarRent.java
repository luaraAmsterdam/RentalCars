/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.person.DbRenter;
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

/**
 *
 * @author Laura
 */
public class DbCarRent extends CarRent{
    private int idRenter;
    
    public DbCarRent(int id, int idRenter, Car car, Insurance insurance, String dateFrom, String dateTo, float resultCost, 
            String insurer, String owner, String status) {
        super(id, car, insurance, dateFrom, dateTo, resultCost, insurer, owner, status);
        this.idRenter = idRenter;
    }

    @Override
    public void save() {
        String str = "INSERT INTO Car_rent (id_car,id_renter,id_insurance,date_from,date_to,result_cost, insurer_approve, owner_approve, car_status)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        updateOrInsert(str);
        DB.commit();
    }

    @Override
    public void update() {
        System.out.println("id carrent " + getCarRentId());
        System.out.println(getInsurerApprove());
        System.out.println(getCarStatus());
        String str = "update Car_rent set insurer_approve= '" + getInsurerApprove() +
                "', owner_approve = '" + getOwnerApprove() + "', car_status = '" + getCarStatus() + "' where id = " + getCarRentId();
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(str);            
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        DB.commit();
    }
    
    @Override
    public void remove() {
        String str = "delete from Car_rent where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(str);
	    ps.setInt(1, getCarRentId());
	    ps.execute();
            DB.commit();
	} catch (SQLException ex) {
	    Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static CarRent parse(ResultSet set) throws SQLException {
        int idCar = set.getInt("id_car");
        System.out.println("carr " + idCar);
        int idRenter = set.getInt("id_renter");
        int idInsurance = set.getInt("id_insurance");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateFrom = df.format(set.getDate("date_from"));
        String dateTo= df.format(set.getDate("date_to"));
        int resultCost = set.getInt("result_cost");
        String insurerApprove = set.getString("insurer_approve");
        String ownerApprove = set.getString("insurer_approve");
        String statusCar = set.getString("car_status");
        
        CarRent carRent = new DbCarRent(set.getInt("id"), idRenter, null, null, dateFrom, dateTo, resultCost, 
                insurerApprove, ownerApprove, statusCar);
        Car car = getCarById(idCar);
        carRent.setCar(car);
        Insurance insurance = getInsuranceById(idInsurance);
        carRent.setInsurance(insurance);
        return carRent;
    }
    
    public static CarRent getCarRentById(int id) {
        System.out.println("idddd " + id);
        String query = "SELECT * FROM CAR_RENT WHERE ID = " + id;
        return getCarRent(query).get(0);
    }
    
    private static List<CarRent> getCarRent(String query) {
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
    
    private void updateOrInsert(String str) {
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            Car car = getCarRentCar();
            Insurance insurance = getCarRentInsurance();
            ps.setInt(1, car.getCarId());
            ps.setInt(2, idRenter);
            ps.setInt(3, insurance.getInsuranceId());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateF;
            try {
                dateF = format.parse(getCarRentDateFrom());
                ps.setDate(4, new java.sql.Date(dateF.getTime()));
                ps.setDate(5, new java.sql.Date(dateF.getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(DbCarRent.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setFloat(6, getCarRentResultCost());
            ps.setString(7, getInsurerApprove());
            ps.setString(8, getOwnerApprove());
            ps.setString(9, getCarStatus());
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) 
                setCarRentId(rs.getInt(1));     
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private static Car getCarById(int id) {
        String query = "SELECT * FROM Car WHERE ID = " + id;
        return getCars(query).get(0);
    }

    private static Insurance getInsuranceById(int id) {
        String query = "SELECT * FROM Insurance WHERE ID = " + id;
        return getInsurance(query).get(0);
    }
    
    private static List<Car> getCars(String query) {
        List<Car> cars = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
                cars.add(DbCar.parse(rs));
        } catch (SQLException ex) {
            Logger.getLogger(DbCarRent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
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
            Logger.getLogger(DbCarRent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insurances;
    }
}
