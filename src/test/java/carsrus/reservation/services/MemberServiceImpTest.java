package carsrus.reservation.services;

import carsrus.reservation.dtos.MemberDTO;
import carsrus.reservation.entities.Member;
import carsrus.reservation.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceImpTest {
    @Autowired
    MemberRepository memberRepository;
    MemberServiceImp memberServiceImp;

    @BeforeEach
    void setUp() {
        memberServiceImp = new MemberServiceImp(memberRepository);
    }

    @Test
    @Sql("/createMembers.sql")
    void getAllMembers() {
        long count = memberServiceImp.getAllMembers(true).size();
        assertEquals(3,count);
    }

    @Test
    @Sql("/createMembers.sql")
    void findMemberById() {
        MemberDTO memberDTO = memberServiceImp.findMemberById(1);
        assertEquals("a1",memberDTO.getFirstName());
    }

    @Test
    @Sql("/createMembers.sql")
    void saveEditedCustomer() {
        MemberDTO memberDTO = memberServiceImp.findMemberById(2);
        assertEquals(2,memberDTO.getId());
        memberDTO.setFirstName("xxx");
        MemberDTO memberDTOEdited = memberServiceImp.saveEditedCustomer(memberDTO);
        assertEquals("xxx", memberDTOEdited.getFirstName());
        assertEquals(2, memberDTOEdited.getId());
    }
}