package carsrus.reservation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReservationID implements Serializable {
    private int carId;
    private int memberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationID)) return false;
        ReservationID that = (ReservationID) o;
        return getCarId() == that.getCarId() && getMemberId() == that.getMemberId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getMemberId());
    }
}
