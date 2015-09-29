/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.DB;
import db.person.DbAdmin;
import db.person.DbInsurer;
import db.person.DbOwner;
import db.person.DbPerson;
import db.person.DbRenter;
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
import logicLevel.person.Admin;
import logicLevel.person.Insurer;
import logicLevel.person.Owner;
import logicLevel.person.Person;
import logicLevel.person.Renter;
import servicelevel.AdminService;
import servicelevel.AdminServiceImpl;
import servicelevel.InsurerService;
import servicelevel.InsurerServiceImpl;
import servicelevel.OwnerService;
import servicelevel.OwnerServiceImpl;
import servicelevel.PersonService;
import servicelevel.PersonServiceImpl;
import servicelevel.UserService;
import servicelevel.UserServiceImpl;

/**
 *
 * @author Laura
 */
public class CarRentMain {

    public static final String UBIND = "PC/user";
    public static final String PBIND = "PC/person";
    public static final String ABIND = "PC/admin";
    public static final String OBIND = "PC/owner";
    public static final String IBIND = "PC/insurer";
    //Logic level
    public static Admin adminLL = new DbAdmin();
    public static Insurer insurerLL = new DbInsurer();
    public static Owner ownerLL = new DbOwner();
    public static Person personLL = new DbPerson();
    public static Renter renternLL = new DbRenter();
    //Service level
    public static OwnerService ownerService = null;
    public static PersonService personService = null;
    public static InsurerService insurerService = null;
    public static AdminService adminService = null;
    public static UserService userService = null;
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {
        Registry registry = LocateRegistry.createRegistry(3000);
        System.out.println("Service register OK");
        
        userService = new UserServiceImpl();
        UserService ustub = (UserService)UnicastRemoteObject.exportObject(userService, 0);
        registry.bind(UBIND, ustub);
        System.out.println("Service USER BIND success");
        
        personService = new PersonServiceImpl();
        PersonService pstub = (PersonService)UnicastRemoteObject.exportObject(personService, 0);
        registry.bind(PBIND, pstub);
        System.out.println("Service PERSON BIND success");
        
        adminService = new AdminServiceImpl();
        AdminService astub = (AdminService)UnicastRemoteObject.exportObject(adminService, 0);
        registry.bind(ABIND, astub);
        System.out.println("Service ADMIN BIND success");
        
        insurerService = new InsurerServiceImpl();
        Remote istub = UnicastRemoteObject.exportObject(insurerService, 0);
        registry.bind(IBIND, istub);
        System.out.println("Service INSURER BIND success");
        
        ownerService = new OwnerServiceImpl();
        Remote ostub = UnicastRemoteObject.exportObject(ownerService, 0);
        registry.bind(OBIND, ostub);
        System.out.println("Service OWNER BIND success");

    }
}
