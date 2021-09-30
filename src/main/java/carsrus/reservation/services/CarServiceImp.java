package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.dtos.CarInput;
import carsrus.reservation.entities.Car;
import carsrus.reservation.exceptions.ResourceNotFoundException;
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

    public static List<CarDTO> carDTOsFromCars(Iterable<Car> cars) {
        return StreamSupport.stream(cars.spliterator(), false)
                .map(car -> new CarDTO(car))
                .collect(Collectors.toList());
    }

    public static Car carFromCarDTO(CarInput carInput){
        return new Car(carInput.getBrand(),carInput.getModel(),carInput.getPricePerDay());
    }

    private String errorMessage(long id){
        return "Resource Not found with id = " + id;
    }
    private String errorMessageList() {
        return "No cars found";
    }
    private String errorMessagePrice() {
        return "No car was found with that price";
    }

    @Override
    public List<CarDTO> getCars(String brand, String model) {
        if (brand == null && model != null) {
            throw new IllegalArgumentException("Brand is required when model is supplied");
        } else if (brand != null && model != null) {
            return carDTOsFromCars(carRepository.findCarByBrandAndModel(brand, model));
        } else if (brand != null) {
            return carDTOsFromCars(carRepository.findCarByBrand(brand));
        } else if (carRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException(errorMessageList());
        }
        return carDTOsFromCars(carRepository.findAll());
    }

    @Override
    public List<CarDTO> getCarsByPrice(double price, boolean equal) {
        if (carRepository.findCarByPricePerDayLessThanEqual(price).isEmpty()) {
            throw new ResourceNotFoundException(errorMessagePrice());
        }
        if (equal) {
            return carDTOsFromCars(carRepository.findCarByPricePerDayLessThanEqual(price));
        }
        return carDTOsFromCars(carRepository.findCarByPricePerDayLessThan(price));
    }

    @Override
    public CarDTO getCarDTOById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
        return new CarDTO(car);
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(id)));
    }

    @Override
    public CarDTO addCar(CarInput carInput) {
        Car carToAdd = carFromCarDTO(carInput);
        return new CarDTO(carRepository.save(carToAdd));
    }

    @Override
    public CarDTO editCar(CarDTO carEdited) {
        Car carInDB = carRepository.findById(carEdited.getId()).orElseThrow();
        String brand = carEdited.getBrand();
        String model = carEdited.getModel();
        double price = carEdited.getPricePerDay();
        if (brand != null) {
            carInDB.setBrand(brand);
        }
        if (model != null) {
            carInDB.setModel(model);
        }
        if (price != carInDB.getPricePrDay()) {
            carInDB.setPricePrDay(price);
        }
        return new CarDTO(carRepository.save(carInDB));
    }

    @Override
    public void removeCar(int id) {
        carRepository.deleteById(id);
    }
}
