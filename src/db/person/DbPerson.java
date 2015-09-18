/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;
import db.DB;
import db.DbCar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbPerson extends Person {

    public DbPerson(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    public static void save(String name, String email, String password, String role) {
        String str = "INSERT INTO Person (name,mail, password, role)"
                + " VALUES (?, ?, ?, ?)";
        updateOrInsert(str, name, email, password, role);
    }

    public static void update(String name, String email, String password, String role) {
        String str = "update Person set name=?,mail=?,password=?,role=?";
        updateOrInsert(str, name, email, password, role);
    }

    public static void remove(int id) {
        String str = "delete from Person where id = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }    

    private static int updateOrInsert(String str, String name, String email, String password, String role) {
        int id = -1;
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) 
                id = rs.getInt(1);     
        } catch (SQLException ex) {
            Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
//
//        public static Car parse(ResultSet set) throws SQLException {
//        int owner_id = set.getInt("id_owner");
//        Owner owner = (Owner) DbService.getPersonById(owner_id);
//        Car car = new DbCar(set.getString("vin"), set.getString("mark"), set.getString("model"),
//                set.getFloat("volume"), set.getFloat("power"), set.getString("color"), set.getFloat("cost"),
//                owner);
//        car.setCarId(set.getInt("id"));
//        return car;
//    }
    
    public static Person parse(ResultSet set) throws SQLException {
        Person person = new DbPerson(set.getInt("id"), set.getString("name"), set.getString("mail"),
                set.getString("password"), set.getString("role"));
        return person;
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
