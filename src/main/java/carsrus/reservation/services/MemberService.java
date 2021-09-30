package carsrus.reservation.services;

import carsrus.reservation.dtos.MemberDTO;
import carsrus.reservation.dtos.MemberInput;
import carsrus.reservation.entities.Member;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMembers(boolean simple);

    MemberDTO addMember(MemberInput memberInput);

    MemberDTO findMemberDTOById(int id);

    Member findMemberById(int id);

    MemberDTO saveEditedCustomer(MemberDTO memberDTO);

    void removeMember(int memberId);
}
