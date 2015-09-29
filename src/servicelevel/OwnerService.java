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

/**
 *
 * @author Laura
 */
public interface OwnerService extends Remote{
        //Owner
    List<Object> getCarByMarkForOwner(String mark, String model, int id) throws RemoteException;
    List<Object> getAllCarForOwner(int id) throws RemoteException;
    List<Object> getHistoryForCar(int id) throws RemoteException;
    void deleteCarById(int id) throws RemoteException;
    Object addNewCar(int idOwner, String mark, String model, float power, float volume,
            String color, float cost) throws RemoteException;
    void approveOwnerOrder(int id, String approve) throws RemoteException;
    List<Object> getUnreviewedOrderForOwner(int id) throws RemoteException;
}
