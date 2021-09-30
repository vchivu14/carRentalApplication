package carsrus.reservation.dtos;

import carsrus.reservation.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {
    private Integer reservationId;
    private LocalDate localDate;
    private String memberName;
    private String carType;
    private String memberEmail;
}
