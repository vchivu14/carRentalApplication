package carsrus.reservation.repositories;

import carsrus.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findReservationByReservedCar_CarIdAndRentalDate(int reservedCarId, LocalDate date);
}
