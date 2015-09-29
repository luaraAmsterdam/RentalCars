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
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class UserServiceImpl implements UserService{
    @Override
    public List<Object> getAllCar() throws RemoteException {
        List<Car> cars = CarRentMain.renternLL.getAllCar();
        List<Object> res = parseCar(cars);
        return res;
    }
    
    @Override
    public List<Object> getAllInsurance() throws RemoteException {
        List<Insurance> insurances = CarRentMain.renternLL.getAllInsurance();
        List<Object> res = parseInsurance(insurances);
        return res;
    }
    
    @Override
    public List<Object> getAllCarRentForUser(int idRenter) throws RemoteException {
        List<CarRent> cars = CarRentMain.renternLL.getAllCarRentForUser(idRenter);
        List<Object> res = new ArrayList();
        for(CarRent c : cars) {
            res.add(parseCarRent(c));
        }
        return res;
    }
    
    
   
    //Client
    @Override
    public Object getCarRentById(int id) throws RemoteException {
        CarRent c = CarRentMain.renternLL.getCarRentById(id);
        return parseCarRent(c);
    }
    
    @Override
    public List<Object> getCarByModel(String mark, String model, String color) {
        List<Car> cars = CarRentMain.renternLL.getCarByModel(mark, model, color);
        return parseCar(cars);
    }

    @Override
    public List<Object> getCarByPower(float power, float volume) {
        List<Car> cars = CarRentMain.renternLL.getCarByPower(power, volume);
        return parseCar(cars);
    }

    @Override
    public List<Object> getCarByCost(float minCost) {
        List<Car> cars = CarRentMain.renternLL.getCarByCost(minCost);
        return parseCar(cars);
    }


    @Override
    public Object addCarRentOrder(int id_Renter, int id_car, int id_insurance, String from, String to) throws RemoteException {
        CarRent cr = CarRentMain.renternLL.addCarRentOrder(id_Renter, id_car, id_insurance, from, to);
        return parseCarRent(cr);
    }


    @Override
    public List<Object> getInsuranceByType(String type) throws RemoteException {
        List <Insurance> ins = CarRentMain.renternLL.getInsuranceByType(type);
        return parseInsuranceWithoutName(ins);
    }

    @Override
    public List<Object> getInsuranceByInsurer(String name) throws RemoteException {
        List <Insurance> ins = CarRentMain.renternLL.getInsuranceByInsurer(name);
        return parseInsuranceWithoutName(ins);
    }

    
    private Object parseCarRent(CarRent c) {
        Car car = c.getCarRentCar();
        Insurance insurance = c.getCarRentInsurance();
        return new Object[]{
                c.getCarRentId(), car.getCarMark(), car.getCarModel(), car.getCarVolume(),
                car.getCarPower(), car.getCarColor(), insurance.getInsuranceType(), 
                c.getCarRentDateFrom(), c.getCarRentDateTo(), c.getCarRentResultCost(), 
                c.getInsurerApprove(), c.getOwnerApprove()
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

}
