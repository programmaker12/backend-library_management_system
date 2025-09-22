package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.LibraryDTO;
import com.thinkinnovative.library_management_system.entity.Department;
import com.thinkinnovative.library_management_system.entity.Library;
import com.thinkinnovative.library_management_system.entity.LibraryBranch;
import com.thinkinnovative.library_management_system.repository.DepartmentRepository;
import com.thinkinnovative.library_management_system.repository.Library1Repository;
import com.thinkinnovative.library_management_system.repository.LibraryBranchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class Library1ServiceImpl {

    @Autowired
    private final Library1Repository libraryRepository;
    private final LibraryBranchRepository libraryBranchRepository;
    private final DepartmentRepository departmentRepository;

    private static final Logger logger = Logger.getLogger(Library1ServiceImpl.class.getName());

    @Transactional
    public void saveLibraryAndBranch(LibraryDTO dto) {
        try {
            // Create and save LibraryBranch
            LibraryBranch branch = LibraryBranch.builder()
                    .branchCode(dto.getBranchCode())
                    .branchName(dto.getBranchName())
                    .branchAddress(dto.getBranchAddress())
                    .openingTime(dto.getBranchOpeningTime())
                    .closingTime(dto.getBranchClosingTime())
                    .year(dto.getBranchYear())
                    .build();
            branch.calculateHours(); // Calculate hours before saving
            LibraryBranch savedBranch = libraryBranchRepository.save(branch);
            logger.info("Library branch saved successfully: " + savedBranch);

            // Create and save Department
            Department department = Department.builder()
                    .departmentName(dto.getDepartmentName())
                    .departmentCode(dto.getDepartmentCode())
                    .bookNumbers(dto.getBookNumbers())
                    .branch(savedBranch)
                    .build();
            departmentRepository.save(department);
            logger.info("Department saved successfully: " + department);

            // Create and save Library
            Library library = Library.builder()
                    .libraryName(dto.getLibraryName())
                    .libraryCode(dto.getLibraryCode())
                    .address(dto.getLibraryAddress())
                    .openingTime(dto.getLibraryOpeningTime())
                    .closingTime(dto.getLibraryClosingTime())
                    .totalBranches(dto.getLibraryTotalBranches())
                    .branch(savedBranch) // Set the saved branch
                    .build();
            library.calculateHours(); // Calculate hours before saving
            libraryRepository.save(library);
            logger.info("Library saved successfully: " + library);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while saving the library and branch: " + e.getMessage(), e);
            throw e;
        }
    }
}
