package carsrus.reservation.repositories;

import carsrus.reservation.testUtils.TestDataMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        TestDataMaker.makeCars(carRepository);
    }

    @Test
    void findAll() {
        assertEquals(3, carRepository.count());
    }

    @Test
    void findCarByBrand() {
        long ferrarisFound = carRepository.findCarByBrand("ferrari").size();
        assertEquals(1, ferrarisFound);
    }

    @Test
    void findCarByBrandAndModel() {
        long ferrarisF50Found = carRepository.findCarByBrandAndModel("ferrari","f50").size();
        long ferrarisf100Found = carRepository.findCarByBrandAndModel("ferrari", "f100").size();
        assertEquals(1, ferrarisF50Found);
        assertNotEquals(1, ferrarisf100Found);
    }

    @Test
    void findCarByPricePerDayLessThan() {
        long carsFound = carRepository.findCarByPricePerDayLessThan(800).size();
        assertEquals(1, carsFound);
    }

    @Test
    void findCarByPricePerDayLessThanEqual() {
        long carsFound = carRepository.findCarByPricePerDayLessThanEqual(800).size();
        assertEquals(2, carsFound);
    }
}