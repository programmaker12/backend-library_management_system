package com.thinkinnovative.library_management_system.dto;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDTO {

    // Library fields
    private String libraryName;
    private String libraryCode;
    private String libraryAddress;
    private LocalTime libraryOpeningTime;
    private LocalTime libraryClosingTime;
    private Integer libraryTotalBranches;

    // Branch fields
    private String branchCode;
    private String branchName;
    private String branchAddress;
    private LocalTime branchOpeningTime;
    private LocalTime branchClosingTime;
    private Integer branchYear;

    //Department fields
    //private Integer department_id;
    private String departmentName;      // Maps to department_name
    private String departmentCode;      // Maps to department_code
    private Integer bookNumbers;        // Maps to book_number
    //private Integer branchId;

}
