package carsrus.reservation.repositories;

import carsrus.reservation.entities.Reservation;
import carsrus.reservation.entities.ReservationID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationID> {
    Reservation findReservationByReservedCar_CarIdAndRentalDate(int reservedCarId, LocalDate date);
}
