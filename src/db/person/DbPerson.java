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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.person.Person;

/**
 *
 * @author Laura
 */
public class DbPerson extends Person {
    public DbPerson(){};
    public DbPerson(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    public static void save(String name, String email, String password, String role) {
        String str = "INSERT INTO Person (name,mail, password, role)"
                + " VALUES (?, ?, ?, ?)";
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
    
    public static Person parse(ResultSet set) throws SQLException {
        Person person = new DbPerson(set.getInt("id"), set.getString("name"), set.getString("mail"),
                set.getString("password"), set.getString("role"));
        return person;
    }

    @Override
    public Person login(String email, String password) {
        String query = "SELECT * FROM PERSON WHERE MAIL = '" 
                    + email + "' AND PASSWORD='" + password + "'";
        return getPersonList(query).get(0);
    }
    
    public static Person getPersonById(int id) {
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
            Logger.getLogger(DbPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
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
