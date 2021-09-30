package carsrus.reservation.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(ReservationID.class)
public class Reservation {
    @Id
    @Column
    private int memberId;

    @Id
    @Column
    private int carId;

    @Column
    private int reservationNo;

    @CreationTimestamp
    private LocalDate reservationDate;

    //@JsonFormat(pattern = "dd-MM-YYYY")  //Should have been done in a DTO
    @Column
    private LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedTo;

    public Reservation(LocalDate rentalDate, Car reservedCar, Member reservedTo) {
        this.rentalDate = rentalDate;
        this.reservedCar = reservedCar;
        this.reservedTo = reservedTo;
    }

    public Reservation(int memberId, int carId, LocalDate rentalDate) {
        this.memberId = memberId;
        this.carId = carId;
        this.rentalDate = rentalDate;
    }

    public Reservation() {}

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Car getReservedCar() {
        return reservedCar;
    }

    public void setReservedCar(Car reservedCar) {
        this.reservedCar = reservedCar;
    }

    public Member getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(Member reservedTo) {
        this.reservedTo = reservedTo;
    }
}
