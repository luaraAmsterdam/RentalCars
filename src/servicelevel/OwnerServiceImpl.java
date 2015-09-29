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
import main.CarRentMain;

/**
 *
 * @author Laura
 */
public class OwnerServiceImpl implements OwnerService{
    
    @Override
    public List<Object> getCarByMarkForOwner(String mark, String model, int id) {
        List<Car> cars = CarRentMain.ownerLL.getCarByMarkForOwner(mark, model, id);
        return parseCar(cars);
    }

    @Override
    public List<Object> getAllCarForOwner(int id) {
        List<Car> cars = CarRentMain.ownerLL.getAllCarForOwner(id);
        return parseCar(cars);
    }

    @Override
    public List<Object> getHistoryForCar(int id) {
        return CarRentMain.ownerLL.getHistoryForCar(id);
    }

    @Override
    public void deleteCarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object addNewCar(int idOwner, String mark, String model, float power, float volume, String color, float cost) {
        Car car = CarRentMain.ownerLL.addNewCar(mark, model, power, volume, color, cost);
        Object[] obj = new Object[]{car.getCarId(), mark, model, power, volume, color, cost};
        return obj;
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

    @Override
    public void approveOwnerOrder(int id, String approve) throws RemoteException {
        CarRentMain.ownerLL.approveOwnerOrder(id, approve);
    }

    @Override
    public List<Object> getUnreviewedOrderForOwner(int id) throws RemoteException {
         return CarRentMain.ownerLL.getUnreviewedOrderForOwner(id);
    }
}
