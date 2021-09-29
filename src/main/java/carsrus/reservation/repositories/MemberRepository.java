package carsrus.reservation.repositories;

import carsrus.reservation.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends JpaRepository<Member, Integer> {
}
