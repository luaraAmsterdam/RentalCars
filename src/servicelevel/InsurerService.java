/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Laura
 */
public interface InsurerService extends Remote{
    //Insurer
    List<Object> getAllInsuranceForInsurer(int id) throws RemoteException;
    List<Object> getInsuranceByTypeForInsurer(String type, int id) throws RemoteException;
    List<Object> getHistoryForInsurance(int id) throws RemoteException;
    Object addNewInsurance(float cost, String type, int insurer) throws RemoteException;
    List<Object> getUnreviewedOrderForInsurance(int id) throws RemoteException;
    void approveInsuranceOrder(int id, String approve) throws RemoteException;
}
