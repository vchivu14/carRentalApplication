package carsrus.reservation.rest;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.dtos.CarInput;
import carsrus.reservation.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getCars(@RequestParam(required = false) String brand,
                                                @RequestParam(required = false) String model) {
        List<CarDTO> carDTOS = carService.getCars(brand,model);
        return new ResponseEntity<>(carDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable int id) {
        CarDTO carDTO = carService.getCarDTOById(id);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @PostMapping("/addCar")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarInput newCar) {
        CarDTO carDTO = carService.addCar(newCar);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @PutMapping("/editCar")
    public ResponseEntity<CarDTO> editCar(@RequestBody CarDTO carToEdit) {
        CarDTO carDTO = carService.editCar(carToEdit);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removeCar/{id}")
    void removeCar(@PathVariable int id) {
        carService.removeCar(id);
    }
}
