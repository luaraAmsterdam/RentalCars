/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.DB;
import db.DbService;
import db.person.DbPerson;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.Car;
import logicLevel.Insurance;
import logicLevel.Service;
import servicelevel.UserServiceImpl;

/**
 *
 * @author Laura
 */
public class CarRentMain {

    public static final String UBIND = "PC/user";
    public static final Service service = new DbService();

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

//        DbService service = new DbService();
//        service.login("0000@gmail.com", "123456");
//          service.login("12234@gmail.com", "1256");
//          service.getAllCarRentForUser(2);
//          service.getCarRentById(5);
//          service.getCarByCost(50);
//          service.getCarByModel("Mers", "A6", "orange");
//          service.getCarByPower(1.6f, 160);

//       List <Insurance> ins = service.getInsuranceByType("All");
//       for(int i = 0; i < ins.size(); i++) {
//           Insurance in = ins.get(i);
//           System.out.println(in.getInsuranceCost());
//       }
        final Registry registry = LocateRegistry.createRegistry(3000);
        System.out.println("Service register OK");
        
        final UserServiceImpl userService = new UserServiceImpl();
        Remote ustub = UnicastRemoteObject.exportObject(userService, 0);
        
        registry.bind(UBIND, ustub);
        System.out.println("Service USER BIND success");

    }
}



//        service.getAllPerson();
//        Connection c = DB.getConnection();
//        PreparedStatement ps = c.prepareStatement(" SELECT * FROM PERSON ");
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            System.out.println(rs.getString("role"));
//        }
//        c.close();
//        c = DB.getConnection();



//        
//        String str = "SELECT * FROM PERSON";
//        try {
//            PreparedStatement pss = c.prepareCall(str);
//            ResultSet rss = pss.executeQuery();
//            while (rss.next()) {
//                //persons.add(DbPerson.parse(rs));
//                System.out.println(rss.getInt("ID"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        DB.closeConnection();