package carsrus.reservation.services;

import carsrus.reservation.dtos.CarDTO;
import carsrus.reservation.dtos.ReservationDTO;
import carsrus.reservation.dtos.ReservationToEditDTO;
import carsrus.reservation.entities.Car;
import carsrus.reservation.entities.Member;
import carsrus.reservation.entities.Reservation;
import carsrus.reservation.exceptions.ReservationExists;
import carsrus.reservation.exceptions.ResourceNotFoundException;
import carsrus.reservation.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {
    private ReservationRepository reservationRepository;
    private CarService carService;
    private MemberService memberService;

    public ReservationServiceImp(ReservationRepository reservationRepository,
                                 CarService carService,
                                 MemberService memberService) {
        this.reservationRepository = reservationRepository;
        this.carService = carService;
        this.memberService = memberService;
    }

    private ReservationDTO getReservationDTO(Reservation reservation, Car car, Member member) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setMemberName(member.getFirstName()+"_"+member.getLastName());
        reservationDTO.setCarType(car.getBrand()+"_"+car.getModel());
        reservationDTO.setMemberEmail(member.getEmail());
        return reservationDTO;
    }

    private String errorMessage(long id){
        return "Resource Not found with id = " + id;
    }
    private String errorMessageList() {
        return "No reservations found";
    }

    @Override
    public CarDTO isCarFree(int carId, LocalDate date) {
        Reservation r = reservationRepository.findReservationByReservedCar_CarIdAndRentalDate(carId,date);
        if (r != null) {
            throw new ReservationExists("This car is already booked for this date");
        }
        return carService.getCarDTOById(carId);
    }

    private boolean isCarFreeV2(int carId, LocalDate localDate) {
        Reservation r = reservationRepository.findReservationByReservedCar_CarIdAndRentalDate(carId, localDate);
        return r == null;
    }

    @Override
    public ReservationDTO getReservationById(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(reservationId)));
        Member member = reservation.getReservedTo();
        Car car = reservation.getReservedCar();
        return getReservationDTO(reservation, car, member);
    }

    @Override
    public ReservationDTO makeReservation(int carId, int memberId, LocalDate localDate) {
        boolean response = isCarFreeV2(carId, localDate);
        if (response) {
            Member member = memberService.findMemberById(memberId);
            Car car = carService.getCarById(carId);
            Reservation reservation = new Reservation(localDate, car, member);
            return getReservationDTO(reservation, car, member);
        } else {
            throw new ReservationExists("This car is already booked for this date");
        }
    }

    @Override
    public List<CarDTO> getAllFreeCarsForDate(LocalDate date) {
        List<CarDTO> carDTOS = carService.getCars(null,null);
        List<CarDTO> availableCars = new ArrayList<>();
        for (CarDTO c: carDTOS) {
            if (isCarFreeV2(c.getId(), date)) {
                availableCars.add(c);
            }
        }
        return availableCars;
    }

    @Override
    public ReservationDTO editReservation(ReservationToEditDTO editedReservation) {
        Reservation reservation = reservationRepository.getById(editedReservation.getReservationId());
        Car car;
        Member member;
        int carId = editedReservation.getCarId();
        int memberId = editedReservation.getMemberId();
        LocalDate bookedDate = editedReservation.getBookedDate();
        car = carService.getCarById(carId);
        if (car != null) {
            reservation.setReservedCar(car);
        } else {
            car = reservation.getReservedCar();
            reservation.setReservedCar(car);
        }
        member = memberService.findMemberById(memberId);
        if (member != null) {
            reservation.setReservedTo(member);
        } else {
            member = reservation.getReservedTo();
            reservation.setReservedTo(member);
        }
        if (bookedDate != null) {
            reservation.setRentalDate(bookedDate);
        }
        return getReservationDTO(reservation, car, member);
    }

    @Override
    public void cancelReservation(int reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
