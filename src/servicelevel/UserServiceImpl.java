/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelevel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import logicLevel.Car;
import logicLevel.CarRent;
import logicLevel.Insurance;
import logicLevel.person.Person;
import logicLevel.person.Renter;
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class UserServiceImpl implements UserService{
    //
    @Override
    public void register(String name, String email, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object[] login(String email, String password) throws RemoteException {
        Person person = CarRentMain.service.login(email, password);
        Object[] res = new Object[] {person.getPersonId(), person.getPersonName(),
            person.getPersonEmail(), person.getPersonRole()};
        return res;
    }

    @Override
    public List<Object> getAllCar() throws RemoteException {
        List<Car> cars = CarRentMain.service.getAllCar();
        List<Object> res = parseCar(cars);
        return res;
    }
    
    @Override
    public List<Object> getAllInsurance() throws RemoteException {
        List<Insurance> insurances = CarRentMain.service.getAllInsurance();
        List<Object> res = parseInsurance(insurances);
        return res;
    }
    
    @Override
    public List<Object> getAllCarRentForUser(int idRenter) throws RemoteException {
        List<CarRent> cars = CarRentMain.service.getAllCarRentForUser(idRenter);
        List<Object> res = new ArrayList();
        for(CarRent c : cars) {
            res.add(parseCarRent(c));
        }
        return res;
    }
    
    
    //Admin
    @Override
    public void deletePersonById(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getPersonByName(String Name) throws RemoteException {
        Person person = CarRentMain.service.getPersonByName(Name);
        Object res = new Object[]{
                person.getPersonId(), person.getPersonName(), person.getPersonEmail(),
                person.getPersonRole()   };
        return res;
    }
    
    @Override
    public List<Object> getPersonsByRole(String role) throws RemoteException {
        List<Person> persons = CarRentMain.service.getPersonsByRole(role);
        return parsePerson(persons);
    }

    //Client
    @Override
    public Object getCarRentById(int id) throws RemoteException {
        CarRent c = CarRentMain.service.getCarRentById(id);
        return parseCarRent(c);
    }
    
    @Override
    public List<Object> getCarByModel(String mark, String model, String color) {
        List<Car> cars = CarRentMain.service.getCarByModel(mark, model, color);
        return parseCar(cars);
    }

    @Override
    public List<Object> getCarByPower(float power, float volume) {
        List<Car> cars = CarRentMain.service.getCarByPower(power, volume);
        return parseCar(cars);
    }

    @Override
    public List<Object> getCarByCost(float minCost) {
        List<Car> cars = CarRentMain.service.getCarByCost(minCost);
        return parseCar(cars);
    }


    @Override
    public Object addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) throws RemoteException {
        CarRent cr = CarRentMain.service.addCarRentOrder(id_Renter, id_car, id_insurance, from, to);
        return parseCarRent(cr);
    }

    @Override
    public List<Object> getAllPerson() throws RemoteException {
        List<Person> persons = CarRentMain.service.getAllPerson();
        return parsePerson(persons);
    }

    @Override
    public List<Object> getInsuranceByType(String type) throws RemoteException {
        List <Insurance> ins = CarRentMain.service.getInsuranceByType(type);
        return parseInsuranceWithoutName(ins);
    }

    @Override
    public List<Object> getInsuranceByInsurer(String name) throws RemoteException {
        List <Insurance> ins = CarRentMain.service.getInsuranceByInsurer(name);
        return parseInsuranceWithoutName(ins);
    }

    @Override
    public List<Object> getHistoryForInsurance(int id) throws RemoteException {
        return CarRentMain.service.getHistoryForInsurance(id);
    }

    @Override
    public Object addNewInsurance(float cost, String type, int insurer) throws RemoteException {
        Insurance ins = CarRentMain.service.addNewInsurance(cost, type, insurer);
        return new Object[]{ins.getInsuranceId(), ins.getInsuranceType(), ins.getInsuranceCost()};
    }

    @Override
    public List<Object> getAllInsuranceForInsurer(int id) throws RemoteException {
        List<Insurance> insurances = CarRentMain.service.getAllInsuranceForInsurer(id);
        return parseInsuranceWithoutName(insurances);
    }

    @Override
    public List<Object> getInsuranceByTypeForInsurer(String type, int id) throws RemoteException {
        List<Insurance> insurances = CarRentMain.service.getInsuranceByTypeForInsurer(type, id);
        return parseInsuranceWithoutName(insurances);
    }

    @Override
    public List<Object> getCarByMarkForOwner(String mark, String model, int id) {
        List<Car> cars = CarRentMain.service.getCarByMarkForOwner(mark, model, id);
        return parseCar(cars);
    }

    @Override
    public List<Object> getAllCarForOwner(int id) {
        List<Car> cars = CarRentMain.service.getAllCarForOwner(id);
        return parseCar(cars);
    }

    @Override
    public List<Object> getHistoryForCar(int id) {
        return CarRentMain.service.getHistoryForCar(id);
    }

    @Override
    public void deleteCarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car addNewCar(String mark, String model, float power, float volume, String color, float cost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    private Object parseCarRent(CarRent c) {
        Car car = c.getCarRentCar();
        Insurance insurance = c.getCarRentInsurance();
        return new Object[]{
                c.getCarRentId(), car.getCarMark(), car.getCarModel(), car.getCarVolume(),
                car.getCarPower(), car.getCarColor(), insurance.getInsuranceType(), 
                c.getCarRentResultCost(), c.getCarRentDateFrom(), c.getCarRentDateTo()
            };
    }
    
    private List<Object> parseCar(List<Car> cars) {
        List<Object> res = new ArrayList();
        for(Car c : cars) {
            res.add(new Object[]{
                c.getCarId(), c.getCarMark(), c.getCarModel(), c.getCarPower(),
                c.getCarVolume(), c.getCarColor(), c.getCarCost()
            });
        }
        return res;
    }
    
    private List<Object> parseInsurance(List<Insurance> insurances) {
        List<Object> res = new ArrayList();
        for(Insurance insurance : insurances) {
            Person p = insurance.getInsuranceInsurer();
            res.add(new Object[]{
               insurance.getInsuranceId(), p.getPersonName(), 
                insurance.getInsuranceType(), insurance.getInsuranceCost()
            });
        }
        return res;
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
