/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;

import db.DbCarRent;
import db.DbInsurance;
import db.person.DbInsurer;
import db.person.DbPerson;
import java.util.List;
import logicLevel.CarRent;
import logicLevel.Insurance;

/**
 *
 * @author Laura
 */
public abstract class Insurer extends Person{
    private List<Insurance> listInsurance = null;
    public Insurer(){};
    public Insurer(int id, String name, String email, String password, String role, List<Insurance> listInsurance) {
        super(id, name, email, password, role);
        this.listInsurance = listInsurance;
    }
    
    public void setListIns(List<Insurance> ins) {
        this.listInsurance = ins;
    }
    public Insurance addNewInsurance(float cost, String type, int id) {
        Person i = DbPerson.getPersonById(id);
        
        if(cost > 0 && type != null) {
            Insurance ins = new DbInsurance(-1, cost, type, i);
            ins.save();
            return ins;
        } else 
            return null;
        
    }
    
    public void approveInsuranceOrder(int idIns, String status) {
//        for(int i = 0; i < listInsurance.size(); i ++) {
//            if(listInsurance.get(i).getInsuranceId() == idIns) {
                CarRent carRent = DbCarRent.getCarRentById(idIns);
                carRent.setInsurerApprove(status);
                carRent.update();
                
//                break;
//            }
//        }
    }
    
    public abstract List<Insurance> getAllInsuranceForInsurer(int id);
    public abstract List<Insurance> getInsuranceByTypeForInsurer(String type, int id);
    public abstract List<Object> getHistoryForInsurance(int id);
    public abstract List<Object> getUnreviewedOrderForInsurance(int id);
}
