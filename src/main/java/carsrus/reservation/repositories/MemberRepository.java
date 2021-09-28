package carsrus.reservation.repositories;

import carsrus.reservation.entities.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, Integer> {
}
