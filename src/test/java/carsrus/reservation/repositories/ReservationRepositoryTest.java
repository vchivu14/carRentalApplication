package carsrus.reservation.repositories;

import carsrus.reservation.entities.Car;
import carsrus.reservation.entities.Member;
import carsrus.reservation.entities.Reservation;
import carsrus.reservation.entities.ReservationID;
import carsrus.reservation.testUtils.TestDataMaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CarRepository carRepository;

    int carId;
    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll();
        memberRepository.deleteAll();
        carRepository.deleteAll();
        Car carToUse = carRepository.save(new Car("volvo","x50",500));
        carId = carToUse.getCarId();
        Member memberToUse = memberRepository.save(new Member("xxx","yyy","zzz"));
        reservationRepository.save(new Reservation
                (LocalDate.of(2021, Month.NOVEMBER,4),carToUse,memberToUse));
    }

    @AfterEach
    void cleanDB() {
        reservationRepository.deleteAll();
        memberRepository.deleteAll();
        carRepository.deleteAll();
    }

    @Test
    void shouldFindReservation() {
        Reservation reservation = reservationRepository.findReservationByReservedCar_CarIdAndRentalDate
                (carId, LocalDate.of(2021, Month.NOVEMBER,4));
        assertNotNull(reservation);
    }

    @Test
    void shouldNotFindReservation() {
        Reservation reservation = reservationRepository.findReservationByReservedCar_CarIdAndRentalDate
                (carId, LocalDate.of(2021, Month.JANUARY,4));
        assertNull(reservation);
    }
}