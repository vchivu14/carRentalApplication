package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.dtos.CarInput;
import carsrus.reservation.entities.Car;

import java.util.List;

public interface CarService {
    List<CarDTO> getCars(String brand, String model);

    CarDTO getCarDTOById(int id);

    Car getCarById(int id);

    CarDTO addCar(CarInput carInput);

    CarDTO editCar(CarDTO carToEdit);

    void removeCar(int id);

    List<CarDTO> getCarsByPrice(double price, boolean equal);
}
