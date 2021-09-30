package carsrus.reservation.rest;

import carsrus.reservation.dtos.MemberDTO;
import carsrus.reservation.repositories.MemberRepository;
import carsrus.reservation.testUtils.TestDataMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {carsrus.reservation.ReservationApplication.class},
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {
    @Autowired
    MemberRepository memberRepository; //used to setup some data

    private final String BASE_PATH = "/api/members";
    private final HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    private static List<Integer> memberIds;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll();
        memberIds = TestDataMaker.returnMemberIds(memberRepository);
    }

    private String makeUrl(String path){
        return "http://localhost:"+port+path;
    }

    private ResponseEntity<List<MemberDTO>> getResponseWithAllMembers() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<List<MemberDTO>> responseEntity = restTemplate.exchange(makeUrl(BASE_PATH),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return responseEntity;
    }

    @Test
    void getAllMembers() {
        ResponseEntity<List<MemberDTO>> responseEntity = getResponseWithAllMembers();
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size());
    }

//    @Test
//    void getMember() {
//    }
//
//    @Test
//    void addMember() {
//    }
//
//    @Test
//    void editMember() {
//    }
//
//    @Test
//    void removeMember() {
//    }
}