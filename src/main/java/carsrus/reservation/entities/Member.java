package carsrus.reservation.entities;

import carsrus.reservation.dtos.MemberInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int memberId;

    @Column(columnDefinition = "CHAR(40)", nullable = false)
    String firstName;

    @Column(columnDefinition = "CHAR(40)", nullable = false)
    String lastName;

    @Column(columnDefinition = "CHAR(40)")
    String street;

    @Column(columnDefinition = "CHAR(40)")
    String city;

    @Column(columnDefinition = "CHAR(40)")
    String zip;

    @Column(columnDefinition = "CHAR(80)",nullable = false,unique = true)
    String email;

    @CreationTimestamp
    LocalDateTime dateCreated;

    @UpdateTimestamp
    LocalDateTime dateUpdated;

    /**
     * We assume that once we have a credit card (NOT PART OF THIS EXERCISE) a Member is approved
     */
    boolean approved;

    /**
     * Ranking of Members for internal use.
     * Valid values -1,0,1,2,3,4,5  Where 5 is a perfect customer and 0 is NOT a perfect customer ;-)
     * -1 indicates NOT SET
     */
    int ranking = -1; //-1 = NOT SET

    @OneToMany(mappedBy = "reservedTo", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    List<Reservation> reservations = new ArrayList<>();

    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Member(MemberInput mi) {
        this.firstName = mi.getFirstName();
        this.lastName = mi.getLastName();
        this.email = mi.getEmail();
        this.street = mi.getStreetName();
        this.city = mi.getCity();
        this.zip = mi.getZipcode();
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setReservedTo(this);
    }
}
