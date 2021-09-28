package carsrus.reservation.services;

import carsrus.reservation.dtos.MemberDTO;
import carsrus.reservation.dtos.MemberInput;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMembers(boolean simple);

    MemberDTO addMember(MemberInput memberInput);

    MemberDTO findMemberById(int id);

    MemberDTO saveEditedCustomer(MemberDTO memberDTO);
}
