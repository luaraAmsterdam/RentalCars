/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.RemoteException;
import logicLevel.person.Person;
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class PersonServiceImpl implements PersonService{

    @Override
    public void register(String name, String email, String password, String role) throws RemoteException {
        CarRentMain.personLL.register(name, email, password, role);
    }
    @Override
    public Object[] login(String email, String password) throws RemoteException {
        Person person = CarRentMain.personLL.login(email, password);
        Object[] res = new Object[] {person.getPersonId(), person.getPersonName(),
            person.getPersonEmail(), person.getPersonRole()};
        return res;
    }
    
}
