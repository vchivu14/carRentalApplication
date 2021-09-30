package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.dtos.ReservationDTO;
import carsrus.reservation.dtos.ReservationToEditDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    CarDTO isCarFree(int carId, LocalDate date);

    ReservationDTO getReservationById(int reservationId);

    ReservationDTO makeReservation(int carId, int memberId, LocalDate localDate);

    List<CarDTO> getAllFreeCarsForDate(LocalDate date);

    ReservationDTO editReservation(ReservationToEditDTO editedReservation);

    void cancelReservation(int reservationId);
}
