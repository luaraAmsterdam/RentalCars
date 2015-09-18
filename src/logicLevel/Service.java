/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import java.util.List;
import logicLevel.person.Person;
import logicLevel.person.Renter;

/**
 *
 * @author Laura
 */
public abstract class Service {
    public abstract void register(String name, String email, String password);
    public abstract Person login(String email, String password);
    
    public abstract List<Car> getAllCar();
    public abstract List<Insurance> getAllInsurance();
    public abstract List<Person> getAllPerson();
    public abstract List<CarRent> getAllCarRentForUser(int id);
    
    //Admin 
    public abstract void deletePersonById(int id);
    public abstract Person getPersonByName(String Name);
    public abstract List<Person> getPersonsByRole(String role);
    
    //Client
    public abstract CarRent getCarRentById(int id);
    public abstract List<Car> getCarByModel(String mark, String model, String color);
    public abstract List<Car> getCarByPower(float power, float volume);
    public abstract List<Car> getCarByCost(float minCost);
    public abstract CarRent addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to);
    public abstract List<Insurance> getInsuranceByType(String type);
    public abstract List<Insurance> getInsuranceByInsurer(String name);
    
    //Owner
    public abstract List<Car> getCarByMarkForOwner(String mark, String model, int id);
    public abstract List<Car> getAllCarForOwner(int id);
    public abstract List<Object> getHistoryForCar(int id);
    public abstract void deleteCarById(int id);
    public abstract Car addNewCar(String mark, String model, float power, float volume,
            String color, float cost);
    
    //Insurer
    public abstract List<Insurance> getAllInsuranceForInsurer(int id);
    public abstract List<Insurance> getInsuranceByTypeForInsurer(String type, int id);
    public abstract List<Object> getHistoryForInsurance(int id);
    public abstract Insurance addNewInsurance(float cost, String type, int insurer);
}
