package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.exceptions.ReservationExists;
import carsrus.reservation.repositories.CarRepository;
import carsrus.reservation.repositories.MemberRepository;
import carsrus.reservation.repositories.ReservationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ReservationServiceImpTest {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarService carService;
    @Autowired
    MemberService memberService;
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;

    ReservationServiceImp reservationServiceImp;

    @BeforeEach
    void initService() {
        reservationServiceImp = new ReservationServiceImp(reservationRepository,carService,memberService);
    }

    @AfterEach
    void cleanDB() {
        reservationRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @Sql("/createReservations.sql")
    void isCarFree() {
        CarDTO carDTOfree = reservationServiceImp.isCarFree(3, LocalDate.now());
        assertNotNull(carDTOfree);
        Exception exception = assertThrows(ReservationExists.class, () -> {
            reservationServiceImp.isCarFree(2,LocalDate.now());
        });
        String expectedMessage = "This car is already booked for this date";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void getReservationById() {
//    }
//
//    @Test
//    void makeReservation() {
//    }
//
//    @Test
//    void getAllFreeCarsForDate() {
//    }
//
//    @Test
//    void editReservation() {
//    }
//
//    @Test
//    void cancelReservation() {
//    }
}