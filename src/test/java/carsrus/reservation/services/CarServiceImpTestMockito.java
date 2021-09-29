package carsrus.reservation.services;

import carsrus.reservation.entities.Car;
import carsrus.reservation.repositories.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarServiceImpTestMockito {
    @Mock
    CarRepository carRepository;

    CarServiceImp carServiceImp;

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        //setup your CarServiceImp using the mocked repository
        carServiceImp = new CarServiceImp(carRepository);

        List<Car> fCars = Arrays.asList(
                new Car("Ferrari","F50", 3000),
                new Car("Lamborghini", "Murcielago", 4000)
        );
        when(carRepository.findAll()).thenReturn(fCars);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void getCars() {
        long count = carServiceImp.getCars(null,null).size();
        assertEquals(2,count);
    }

}