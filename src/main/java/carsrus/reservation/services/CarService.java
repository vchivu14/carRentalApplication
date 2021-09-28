package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getCars(String brand, String model);

    CarDTO getCar(int id);

    CarDTO addCar(CarDTO newCar);

    CarDTO editCar(CarDTO carToEdit);

    void removeCar(int id);

    List<CarDTO> getCarsByPrice(double price, boolean equal);
}
