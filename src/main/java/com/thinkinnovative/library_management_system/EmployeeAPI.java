package com.thinkinnovative.library_management_system;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI {
    public static final Logger logInfo = LoggerFactory.getLogger(EmployeeAPI.class);
    @GetMapping("/{empId}")
    public String getEmployee(String empId)
    {
        logInfo.info("Employee Info logging is enabled.");
        logInfo.debug("Employee Debug is enabled");
        return "Employees found";
    }
}
