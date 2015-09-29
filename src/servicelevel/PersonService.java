/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Laura
 */
public interface PersonService extends Remote{
    void register(String name, String email, String password)  throws RemoteException;
    Object[] login(String email, String password) throws RemoteException;
}
