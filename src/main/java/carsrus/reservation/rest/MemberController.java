package carsrus.reservation.rest;

import carsrus.reservation.dtos.MemberDTO;
import carsrus.reservation.dtos.MemberInput;
import carsrus.reservation.services.MemberService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping
//    List<MemberDTO> getAllMembers(@RequestParam(required = false) String type) {
//        boolean simple = false;
//        if (type != null && type.equals("simple")) {
//            simple = true;
//        }
//        return memberService.getAllMembers(simple);
//    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(@RequestParam(required = false) String type) {
        boolean simple = false;
        if (type != null && type.equals("simple")) {
            simple = true;
        }
        List<MemberDTO> memberDTOS = memberService.getAllMembers(simple);
        return new ResponseEntity<>(memberDTOS, HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}")
//    @ApiModelProperty
//    MemberDTO getMember(@PathVariable int id) {
//        return memberService.findMemberById(id);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable int id) {
        MemberDTO memberDTO = memberService.findMemberById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("/addMember")
    MemberDTO addMember(@RequestBody MemberInput memberInput) {
        return memberService.addMember(memberInput);
    }

    @PutMapping("/editMember")
    MemberDTO editMember(@RequestBody MemberDTO memberDTO) {
        return memberService.saveEditedCustomer(memberDTO);
    }

    @DeleteMapping("/removeMember/{id}")
    @ApiOperation("Delete the member with the provided id. The response does NOT contain anything in the body")
    void removeMember(@PathVariable int id) {
        memberService.removeMember(id);
    }
}
