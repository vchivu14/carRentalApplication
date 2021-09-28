package carsrus.reservation.services;

import carsrus.reservation.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CarServiceImpTest {
    @Autowired
    CarRepository carRepository;

    CarServiceImp carServiceImp;

    @BeforeEach
    public void initService() {
        carServiceImp = new CarServiceImp(carRepository);
    }

    @Test
    @Sql("/createCars.sql")
    public void getExpectedNoOfCars() {
        long count = carServiceImp.getCars(null,null).size();
        assertEquals(3,count);
    }

    @Test
    @Sql("/createCars.sql")
    public void getCarsByPrice() {
        long count = carServiceImp.getCarsByPrice(800,true).size();
        assertEquals(2,count);
    }
}
