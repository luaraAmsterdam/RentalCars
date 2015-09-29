/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import logicLevel.Insurance;
import logicLevel.person.Person;
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class InsurerServiceImpl implements InsurerService{
    @Override
    public List<Object> getHistoryForInsurance(int id) throws RemoteException {
        return CarRentMain.insurerLL.getHistoryForInsurance(id);
    }

    @Override
    public Object addNewInsurance(float cost, String type, int insurer) throws RemoteException {
        Insurance ins = CarRentMain.insurerLL.addNewInsurance(cost, type, insurer);
        return new Object[]{ins.getInsuranceId(), ins.getInsuranceType(), ins.getInsuranceCost()};
    }

    @Override
    public List<Object> getAllInsuranceForInsurer(int id) throws RemoteException {
        List<Insurance> insurances = CarRentMain.insurerLL.getAllInsuranceForInsurer(id);
        return parseInsuranceWithoutName(insurances);
    }

    @Override
    public List<Object> getInsuranceByTypeForInsurer(String type, int id) throws RemoteException {
        List<Insurance> insurances = CarRentMain.insurerLL.getInsuranceByTypeForInsurer(type, id);
        return parseInsuranceWithoutName(insurances);
    }

    
    private List<Object> parseInsuranceWithoutName(List<Insurance> insurances) {
        List<Object> res = new ArrayList();
        for(Insurance insurance : insurances) {
            Person p = insurance.getInsuranceInsurer();
            res.add(new Object[]{
               insurance.getInsuranceId(), 
                insurance.getInsuranceType(), insurance.getInsuranceCost()
            });
        }
        return res;
    }

    @Override
    public List<Object> getUnreviewedOrderForInsurance(int id) throws RemoteException {
        return CarRentMain.insurerLL.getUnreviewedOrderForInsurance(id);
    }

    @Override
    public void approveInsuranceOrder(int id, String approve) throws RemoteException {
        CarRentMain.insurerLL.approveInsuranceOrder(id, approve);
    }
}
