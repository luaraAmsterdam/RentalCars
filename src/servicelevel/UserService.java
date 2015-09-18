/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;
import logicLevel.person.Person;
import logicLevel.person.Renter;

/**
 *
 * @author Laura
 */
public interface UserService extends Remote{
    void register(String name, String email, String password)  throws RemoteException;
    Object[] login(String email, String password) throws RemoteException;
    
    List<Object> getAllCar() throws RemoteException;
    List<Object> getAllPerson() throws RemoteException;
    List<Object> getAllInsurance() throws RemoteException;
    List<Object> getAllCarRentForUser(int id) throws RemoteException;
    
    //Admin 
    void deletePersonById(int id) throws RemoteException;
    Object getPersonByName(String Name) throws RemoteException;
    List<Object> getPersonsByRole(String role) throws RemoteException;
    
    //Client
    Object getCarRentById(int id) throws RemoteException;
    List<Object> getCarByModel(String mark, String model, String color) throws RemoteException;
    List<Object> getCarByPower(float power, float volume) throws RemoteException;
    List<Object> getCarByCost(float minCost) throws RemoteException;
    List<Object> getInsuranceByType(String type)throws RemoteException;
    List<Object> getInsuranceByInsurer(String name) throws RemoteException;
    Object addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) throws RemoteException;
    
    //Owner
    List<Object> getCarByMarkForOwner(String mark, String model, int id) throws RemoteException;
    List<Object> getAllCarForOwner(int id) throws RemoteException;
    List<Object> getHistoryForCar(int id) throws RemoteException;
    void deleteCarById(int id) throws RemoteException;
    Car addNewCar(String mark, String model, float power, float volume,
            String color, float cost) throws RemoteException;
    
    //Insurer
    List<Object> getAllInsuranceForInsurer(int id) throws RemoteException;
    List<Object> getInsuranceByTypeForInsurer(String type, int id) throws RemoteException;
    List<Object> getHistoryForInsurance(int id) throws RemoteException;
    Object addNewInsurance(float cost, String type, int insurer) throws RemoteException;
}
