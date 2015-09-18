///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package servicelevel;
//
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//
///**
// *
// * @author Laura
// */
//public class Service {
//    final Registry registry;
//    final UserServiceImpl userService;
//    public Service() throws RemoteException
//    {
//        this.registry = LocateRegistry.createRegistry(3000);
//        System.out.println("Service register OK");
//        
//        this.userService = new UserServiceImpl();
//        Remote stub = UnicastRemoteObject.exportObject(this.userService, 0);
//    }
//}
