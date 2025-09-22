package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.LibraryDTO;
import com.thinkinnovative.library_management_system.service.serviceImpl.Library1ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library1")
@RequiredArgsConstructor
public class Library1Controller {
    private final Library1ServiceImpl libraryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveLibraryAndBranch(@RequestBody LibraryDTO dto) {
        try {
            libraryService.saveLibraryAndBranch(dto);
            return ResponseEntity.ok("Library and Branch saved successfully!");
        } catch (IllegalArgumentException e) {
            // Handle invalid arguments
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            // Catch all other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving Library and Branch: " + e.getMessage());
        }
    }
}
