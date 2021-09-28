package carsrus.reservation.dtos;

import carsrus.reservation.entities.Car;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    int id;
    String brand;
    String model;
    double pricePerDay;

    //Use this for incoming JSON (Create)
    public CarDTO(String brand, String model, double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public CarDTO(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePrDay();
        this.id = car.getCarId();
    }

}