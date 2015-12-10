/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel.person;
 
import db.DbCar;
import db.DbCarRent;
import java.rmi.RemoteException;
import java.util.List;
import logicLevel.Car;
import logicLevel.CarRent;


/**
 *
 * @author Laura
 */
public abstract class Owner extends Person {
    private List<Car> listCar = null;
    public Owner(){};
    public Owner(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
    
    public void setCarRent(List<Car> cars) {
        this.listCar = cars;
    }
    
    public void approveOwnerOrder(int idCar, String status) {
        CarRent carRent = DbCarRent.getCarRentById(idCar);
        carRent.setOwnerApprove(status);
        String insurer = carRent.getInsurerApprove();
        if(status.equals("approved") && insurer.equals("approved")) 
            carRent.setCarStatus("approved");
        else if (status.equals("not approved"))
            carRent.setCarStatus("not approved");
        carRent.update();
    }
    
    public abstract List<Object> getUnreviewedOrderForOwner(int id);
        
    //Owner
    public abstract List<Car> getCarByMarkForOwner(String mark, String model, int id);
    public abstract List<Car> getAllCarForOwner(int id);
    public abstract List<Object> getHistoryForCar(int id);
    public abstract void deleteCarById(int id);
    public Car addNewCar(String mark, String model, float power, float volume,
            String color, float cost) {
        if(mark != null && model != null && power > 0 && volume > 0 &&
                color != null && cost > 0) {
            Car car = new DbCar(-1, mark, mark, model, volume, power, color, cost, this);
            car.save();
            return car;
        } else
            return null;
    }
}
