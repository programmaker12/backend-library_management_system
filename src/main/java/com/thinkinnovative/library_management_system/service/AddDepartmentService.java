package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.AddDepartmentDTO;
import com.thinkinnovative.library_management_system.dto.DepartmentListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddDepartmentService {

    public String addDepartment(AddDepartmentDTO dto);
    public Page<DepartmentListDTO> findAllDepartments(Integer branchid, Pageable pageable);
    public DepartmentListDTO findByDepartmentId(Integer departmentId);
    public DepartmentListDTO findByDepartmentName(String departmentName);
}
