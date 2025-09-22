package com.thinkinnovative.library_management_system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddDepartmentDTO {

    //Department fields
    private String departmentName;      // Maps to department_name
    private String departmentCode;      // Maps to department_code
    private Integer bookNumbers;        // Maps to book_number
    private Integer branchId;

}
