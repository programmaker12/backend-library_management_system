package com.thinkinnovative.payroll.controller;


import com.thinkinnovative.payroll.service.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;



    @PostMapping("/upload-data")
    public String createEmployee(@RequestPart("uploadFile") MultipartFile uploadFile) throws IOException {
        return employeeService.parseExcel(uploadFile.getInputStream());
    }
}
