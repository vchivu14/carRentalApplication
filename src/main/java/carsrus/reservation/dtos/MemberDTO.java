package carsrus.reservation.dtos;

import carsrus.reservation.entities.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String streetName;
    private String city;
    private String zipcode;
    private LocalDateTime dateCreated;

    public MemberDTO(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public MemberDTO(Member m) {
        this.id = m.getMemberId();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.email = m.getEmail();
        this.streetName = m.getStreet();
        this.city = m.getCity();
        this.zipcode = m.getZip();
    }
}