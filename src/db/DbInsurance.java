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
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Insurance;
import logicLevel.person.Insurer;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbInsurance extends Insurance implements Storable{
    private static DbService dbService;
    public DbInsurance(int id, float cost, String type, Person insurer) {
        super(id, cost, type, insurer);
        dbService = new DbService();
    }

    @Override
    public void save() {
        String str = "INSERT INTO Insurance (cost, type, id_insurer) VALUES (?, ?, ?)";
        updateOrOnsert(str);
    }
    
    @Override
    public void update() {
        String str = "UPDATE Insurance SET cost=?, type=?, id_insurer=?";
        updateOrOnsert(str);
    }

    @Override
    public void remove() {
        String str = "delete from insurance where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, getInsuranceId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static Insurance parse(ResultSet set) throws SQLException {
        int insurer_id = set.getInt("id_insurer");
        Insurance ins = new DbInsurance(set.getInt("id"), set.getFloat("cost"), set.getString("type"), null);
        Person insurer = dbService.getPersonById(insurer_id);
        ins.setInsurer(insurer);
        return ins;
    }
    
    private void updateOrOnsert(String str) {
        try {
            
            PreparedStatement ps = DB.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            Person insurer = getInsuranceInsurer();
            
            ps.setFloat(1, getInsuranceCost());
            ps.setString(2, getInsuranceType());
            ps.setInt(3, insurer.getPersonId());
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) 
                setInsuranceId(rs.getInt(1));     
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
