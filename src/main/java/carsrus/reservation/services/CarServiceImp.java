package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.entities.Car;
import carsrus.reservation.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImp implements CarService{
    private CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public static List<CarDTO> carDTOsFromCars(Iterable<Car> cars){
        List<CarDTO> dtos = StreamSupport.stream(cars.spliterator(), false)
                .map(car -> new CarDTO(car))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Car carFromCarDTO(CarDTO carDTO){
        return new Car(carDTO.getBrand(),carDTO.getModel(),carDTO.getPricePerDay());
    }

    @Override
    public List<CarDTO> getCars(String brand, String model) {
        if (brand != null && model != null) {
            return carDTOsFromCars(carRepository.findCarByBrandAndModel(brand,model));
        }
        if (brand != null && model == null) {
            return carDTOsFromCars(carRepository.findCarByBrand(brand));
        }
        return carDTOsFromCars(carRepository.findAll());
    }

    @Override
    public List<CarDTO> getCarsByPrice(double price, boolean equal) {
        if (equal) {
            return carDTOsFromCars(carRepository.findCarByPricePerDayLessThanEqual(price));
        }
        return carDTOsFromCars(carRepository.findCarByPricePerDayLessThan(price));
    }

    @Override
    public CarDTO getCar(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return new CarDTO(car);
    }

    @Override
    public CarDTO addCar(CarDTO newCar) {
        Car carToAdd = carFromCarDTO(newCar);
        return new CarDTO(carRepository.save(carToAdd));
    }

    @Override
    public CarDTO editCar(CarDTO carEdited) {
        Car carInDB = carRepository.findById(carEdited.getId()).orElseThrow();
        String brand = carEdited.getBrand();
        String model = carEdited.getModel();
        Double price = carEdited.getPricePerDay();
        if (brand != null) {
            carInDB.setBrand(brand);
        }
        if (model != null) {
            carInDB.setModel(model);
        }
        if (price != null) {
            carInDB.setPricePrDay(price);
        }
        return new CarDTO(carRepository.save(carInDB));
    }

    @Override
    public void removeCar(int id) {
        carRepository.deleteById(id);
    }
}
