/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import logicLevel.person.Admin;
import logicLevel.person.Person;
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class AdminServiceImpl implements AdminService{ 
    //Admin
    @Override
    public void deletePersonById(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getPersonByName(String Name) throws RemoteException {
        Person person = CarRentMain.adminLL.getPersonByName(Name);
        Object res = new Object[]{
                person.getPersonId(), person.getPersonName(), person.getPersonEmail(),
                person.getPersonRole()   };
        return res;
    }
    
    @Override
    public List<Object> getPersonsByRole(String role) throws RemoteException {
        List<Person> persons = CarRentMain.adminLL.getPersonsByRole(role);
        return parsePerson(persons);
    }
    
    
    @Override
    public List<Object> getAllPerson() throws RemoteException {
        List<Person> persons = CarRentMain.adminLL.getAllPerson();
        return parsePerson(persons);
    }
    
    private List<Object> parsePerson(List<Person> persons) {
        List<Object> res = new ArrayList();
        for(Person p : persons) {
            res.add(new Object[] {
                p.getPersonId(), p.getPersonName(), p.getPersonEmail(),
                p.getPersonRole()                
            });
        }
        return res;
    }
    
}
