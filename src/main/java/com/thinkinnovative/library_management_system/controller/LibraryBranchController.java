package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.LibraryBranchListDTO;
import com.thinkinnovative.library_management_system.service.serviceImpl.LibraryBranchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryBranchController {

    private final LibraryBranchServiceImpl libraryBranchService;

    @GetMapping("/listBranch")
    public List<LibraryBranchListDTO> listBranch()
    {
        return  libraryBranchService.listBranch();
    }
}
