/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static DbService dbService = new DbService();
    
    public DbCarRent(int id, int idRenter, Car car, Insurance insurance, String dateFrom, String dateTo, float resultCost) {
        super(id, car, insurance, dateFrom, dateTo, resultCost);
        this.idRenter = idRenter;
    }

    @Override
    public void save() {
        String str = "INSERT INTO Car_rent (id_car,id_renter,id_insurance,date_from,date_to,result_cost)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        updateOrInsert(str);
    }

    @Override
    public void update() {
        String str = "update Car_rent set id_car=?,id_renter=?,id_insurance=?,date_from=?,date_to=?,"
                + "result_cost=?";
        updateOrInsert(str);
    }
    
    @Override
    public void remove() {
        String str = "delete from Car_rent where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, getCarRentId());
	    ps.execute();
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
        CarRent carRent = new DbCarRent(set.getInt("id"), idRenter, null, null, dateFrom, dateTo, resultCost);
        Car car = dbService.getCarById(idCar);
        carRent.setCar(car);
        Insurance insurance = dbService.getInsuranceById(idInsurance);
        carRent.setInsurance(insurance);
        return carRent;
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
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) 
                setCarRentId(rs.getInt(1));     
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
