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
public interface AdminService extends Remote{
    
    List<Object> getAllPerson() throws RemoteException;
    
    //Admin 
    void deletePersonById(int id) throws RemoteException;
    Object getPersonByName(String Name) throws RemoteException;
    List<Object> getPersonsByRole(String role) throws RemoteException;
    
}
