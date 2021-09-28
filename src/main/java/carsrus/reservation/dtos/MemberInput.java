package carsrus.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInput {
    private String firstName;
    private String lastName;
    private String email;
    private String streetName;
    private String city;
    private String zipcode;
}
