package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.ApiResponse;
import com.thinkinnovative.library_management_system.dto.BorrowTable1DTO;
import com.thinkinnovative.library_management_system.entity.BorrowTable1;
import com.thinkinnovative.library_management_system.service.BorrowTable1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowTable1Controller {

    private final BorrowTable1Service borrowTable1Service;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<Object>> borrowBook(@RequestBody BorrowTable1DTO borrowTable1DTO) {
        BorrowTable1 existingBorrow = borrowTable1Service.findBooksByBookidAndMemberid(
                borrowTable1DTO.getBookId(), borrowTable1DTO.getMemberId());

        if (existingBorrow == null) {
            String responseMessage = borrowTable1Service.borrowBook(borrowTable1DTO);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), responseMessage, null));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), "Book is already borrowed by this member.", null));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse<Object>> returnBook(@RequestBody BorrowTable1DTO borrowTable1DTO) {
        BorrowTable1 existingBorrow = borrowTable1Service.findBooksByBookidAndMemberid(
                borrowTable1DTO.getBookId(), borrowTable1DTO.getMemberId());

        if (existingBorrow == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No borrowed book found for the given details.", null));
        } else {
            String responseMessage = borrowTable1Service.returnBook(existingBorrow);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), responseMessage, null));
        }
    }

    @PostMapping("/member-books")
    public ResponseEntity<ApiResponse<List<BorrowTable1DTO>>> getBooksByMember(@RequestBody Map<String, Integer> request) {
        Integer memberId = request.get("memberID");

        if (memberId == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "memberID is required", null));
        }

        List<BorrowTable1DTO> borrowedBooks = borrowTable1Service.borrowBookByMember(memberId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), null, borrowedBooks));
    }
}
