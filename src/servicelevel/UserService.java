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

/**
 *
 * @author Laura
 */
public interface UserService extends Remote{
    
    List<Object> getAllCar() throws RemoteException;
    List<Object> getAllInsurance() throws RemoteException;
    List<Object> getAllCarRentForUser(int id) throws RemoteException;
    
    
    //Client
    Object getCarRentById(int id) throws RemoteException;
    List<Object> getCarByModel(String mark, String model, String color) throws RemoteException;
    List<Object> getCarByPower(float power, float volume) throws RemoteException;
    List<Object> getCarByCost(float minCost) throws RemoteException;
    List<Object> getInsuranceByType(String type)throws RemoteException;
    List<Object> getInsuranceByInsurer(String name) throws RemoteException;
    Object addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) throws RemoteException;
    

}
