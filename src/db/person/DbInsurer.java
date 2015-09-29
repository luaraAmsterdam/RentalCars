/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import db.DB;
import db.DbInsurance;
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
import logicLevel.Insurance;
import logicLevel.person.Insurer;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbInsurer extends Insurer {
    public DbInsurer(){};
    public DbInsurer(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role, null);
        List<Insurance> ins = getAllInsuranceForInsurer(id);
        super.setListIns(ins);
        
    }
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

    private List<Insurance> getInsurance(String query) {
        List<Insurance> insurances = new ArrayList<Insurance>();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                insurances.add(DbInsurance.parse(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbInsurer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insurances;
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
                    Logger.getLogger(DbInsurer.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.add(new Object[]{from, to, resultCost});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbInsurer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }

    @Override
    public List<Object> getUnreviewedOrderForInsurance(int id) {
        String query = "SELECT Car_rent.id, Insurance.type, Insurance.cost, Car_rent.date_from, Car_rent.date_to "
                + " FROM Car_rent, Insurance where Car_rent.insurer_approve = 'unreviewed' and insurance.id_insurer = " + id 
                + " and Insurance.id = Car_rent.id_insurance";
        List<Object> history = new ArrayList();
        try {
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idIns = rs.getInt("id");
                String type = rs.getString("type");
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
                    Logger.getLogger(DbInsurer.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.add(new Object[]{idIns, type, cost, from, to, resultCost});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbInsurer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
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
