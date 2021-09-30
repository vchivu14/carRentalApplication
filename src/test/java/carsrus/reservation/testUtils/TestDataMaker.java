package carsrus.reservation.testUtils;

import carsrus.reservation.entities.Car;
import carsrus.reservation.entities.Member;
import carsrus.reservation.repositories.CarRepository;
import carsrus.reservation.repositories.MemberRepository;


import java.util.ArrayList;
import java.util.List;

public class TestDataMaker {

    public static List<Member> makeMembers(MemberRepository memberRepository) {
        memberRepository.deleteAll();
        List<Member> members = new ArrayList<>();
        Member m1 = memberRepository.save(new Member("aaa","bbb","ccc"));
        members.add(m1);
        Member m2 = memberRepository.save(new Member("bbb","ccc","aaa"));
        members.add(m2);
        Member m3 = memberRepository.save(new Member("ccc","aaa","bbb"));
        members.add(m3);
        return members;
    }

    public static List<Car> makeCars(CarRepository carRepository) {
        carRepository.deleteAll();
        List<Car> cars = new ArrayList<>();
        Car c1 = carRepository.save(new Car("ferrari", "f50", 1000));
        cars.add(c1);
        Car c2 = carRepository.save(new Car("mercedes", "s100", 800));
        cars.add(c2);
        Car c3 = carRepository.save(new Car("ford", "mustang", 600));
        cars.add(c3);
        return cars;
    }

    public static List<Integer> returnCarIds(CarRepository carRepository) {
        carRepository.deleteAll();
        List<Integer> ids = new ArrayList<>();
        Car c1 = carRepository.save(new Car("ferrari", "f50", 1000));
        ids.add(c1.getCarId());
        Car c2 = carRepository.save(new Car("mercedes", "s100", 800));
        ids.add(c2.getCarId());
        Car c3 = carRepository.save(new Car("ford", "mustang", 600));
        ids.add(c3.getCarId());
        return ids;
    }

    public static List<Integer> returnMemberIds(MemberRepository memberRepository) {
        memberRepository.deleteAll();
        List<Integer> ids = new ArrayList<>();
        Member m1 = memberRepository.save(new Member("aaa","bbb","ccc"));
        ids.add(m1.getMemberId());
        Member m2 = memberRepository.save(new Member("bbb","ccc","aaa"));
        ids.add(m2.getMemberId());
        Member m3 = memberRepository.save(new Member("ccc","aaa","bbb"));
        ids.add(m3.getMemberId());
        return ids;
    }

}
