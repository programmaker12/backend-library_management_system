package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.DepartmentListDTO;
import com.thinkinnovative.library_management_system.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository

public interface DepartmentRepository extends JpaRepository <Department, Integer> {

//    @Query("SELECT new com.thinkinnovative.library_management_system.dto.DepartmentListDTO(" +
//            "d.departmentId, d.departmentName, d.departmentCode, d.bookNumbers, d.branch.branchId, d.branch.branchName) FROM  Department d WHERE d.branch.branchId = :branchid OR null   ")
//    Page<DepartmentListDTO> findAllDepartments(Pageable pageable);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.DepartmentListDTO(" +
            "d.departmentId, d.departmentName, d.departmentCode, d.bookNumbers, " +
            "d.branch.branchId, d.branch.branchName) " +
            "FROM Department d " +
            "WHERE (:branchid IS NULL OR d.branch.branchId = :branchid)")
    Page<DepartmentListDTO> findAllDepartments(@Param("branchid") Integer branchid, Pageable pageable);


    @Query("SELECT new com.thinkinnovative.library_management_system.dto.DepartmentListDTO(" +
            "d.departmentId, d.departmentName, d.departmentCode, d.bookNumbers, d.branch.branchId, d.branch.branchName) FROM  Department d WHERE departmentId = :departmentId")
    DepartmentListDTO findbyDepartmentId(Integer departmentId);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.DepartmentListDTO(" +
            "d.departmentId, d.departmentName, d.departmentCode, d.bookNumbers, d.branch.branchId, d.branch.branchName) FROM  Department d WHERE departmentName = :departmentName")
    DepartmentListDTO findbyDepartmentName(@RequestParam("departmentName") String departmentName);


}
