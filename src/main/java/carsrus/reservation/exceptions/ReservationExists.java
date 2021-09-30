package carsrus.reservation.exceptions;

public class ReservationExists extends RuntimeException {
    public ReservationExists(String msg) {
        super(msg);
    }
}
