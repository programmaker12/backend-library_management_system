package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.AddDepartmentDTO;
import com.thinkinnovative.library_management_system.dto.DepartmentListDTO;
import com.thinkinnovative.library_management_system.entity.Department;
import com.thinkinnovative.library_management_system.entity.LibraryBranch;
import com.thinkinnovative.library_management_system.repository.DepartmentRepository;
import com.thinkinnovative.library_management_system.repository.LibraryBranchRepository;
import com.thinkinnovative.library_management_system.service.AddDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements AddDepartmentService {

    private final LibraryBranchRepository libraryBranchRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    public String addDepartment(AddDepartmentDTO dto) {

        Optional<LibraryBranch> optionallibraryBranch = libraryBranchRepository.findById(dto.getBranchId());
LibraryBranch libraryBranch = optionallibraryBranch.get();
        Department department = Department.builder()
                .departmentName(dto.getDepartmentName())
                .departmentCode(dto.getDepartmentCode())
                .bookNumbers(dto.getBookNumbers())
                .branch(libraryBranch)
                .build();
                departmentRepository.save(department);
        return "Department is added and mapped to" + libraryBranch.getBranchName();
    }

    @Override
    public Page<DepartmentListDTO> findAllDepartments(Integer branchid, Pageable pageable) {
        return departmentRepository.findAllDepartments(branchid, pageable);
    }

    @Override
    public DepartmentListDTO findByDepartmentId(Integer departmentId) {
        return departmentRepository.findbyDepartmentId(departmentId);
    }

    @Override
    public DepartmentListDTO findByDepartmentName(String departmentName) {
        return departmentRepository.findbyDepartmentName(departmentName);
    }
}
