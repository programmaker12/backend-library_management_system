package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.MemberDTO;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import com.thinkinnovative.library_management_system.service.serviceImpl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;


@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private  final MemberService memberService;

//    public MemberController(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    @PostMapping("/addMember")
    public ResponseEntity<String> addMember(@RequestBody MemberDTO memberDTO) {
//        MemberTable memberTable = new MemberTable();
//        memberTable.setMemberName(memberDTO.getMemberName());
//        memberTable.setMembershipDate(LocalDate.now());
//        memberRepository.save(memberTable);

        MemberTable email = memberRepository.findMemberEntityByEmail(memberDTO.getEmail());
        if (email != null) {
            return ResponseEntity.ok("Email is already present");
        } else {
            MemberTable memberTable = MemberTable.builder()
                    .memberName(memberDTO.getMemberName())
                    .email(memberDTO.getEmail())
                    .membershipDate(LocalDate.now())
                    .build();
            memberRepository.save(memberTable);
            return ResponseEntity.ok("Member added successfully in library");
        }
    }

    @GetMapping("/getMember")
    public ResponseEntity<?> getMember() {
        return ResponseEntity.ok(memberRepository.findAllMembers());
    }
    @PostMapping("/member/upload-excel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file)
    {
       try {
           memberService.createMembersExcel(file.getInputStream());
       }
       catch (IOException e)
       {

       }
        return ResponseEntity.ok("ok");
    }



}
