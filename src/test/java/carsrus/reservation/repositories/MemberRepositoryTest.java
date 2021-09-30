package carsrus.reservation.repositories;

import carsrus.reservation.entities.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void cleanDBAfter() {
        memberRepository.deleteAll();
    }

    @Test
    @Sql("/createMembers.sql")
    public void testCount(){
        long result = memberRepository.count();
        assertEquals(3,result);
    }

    @Test
    @Sql("/createMembers.sql")
    public void addMember() {
        Member member = new Member("aaa","bbb","ccc");
        assertEquals(0, member.getMemberId());
        memberRepository.save(member);
        assertTrue(member.getMemberId()>0);
        assertFalse(member.isApproved());
    }

    @Test
    @Sql("/createMembers.sql")
    public void removeMember() {
        memberRepository.deleteById(1);
        long no = memberRepository.count();
        assertEquals(2, no);
    }
}