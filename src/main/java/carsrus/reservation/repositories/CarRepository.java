package carsrus.reservation.repositories;

import carsrus.reservation.entities.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByBrandAndModel(String brand, String model);
    List<Car> findCarByPricePerDayLessThan(double pricePerDay);
    List<Car> findCarByPricePerDayLessThanEqual(double pricePerDay);
}
