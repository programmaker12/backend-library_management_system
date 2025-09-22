package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.AddDepartmentDTO;
import com.thinkinnovative.library_management_system.dto.DepartmentListDTO;
import com.thinkinnovative.library_management_system.service.AddDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private final AddDepartmentService addDepartmentService;

    @PostMapping("/addDepartments")
    public String addDepartment(@RequestBody AddDepartmentDTO dto)
    {
        return addDepartmentService.addDepartment(dto);
    }
    @PostMapping("/allDepartments")
    public Page<DepartmentListDTO> allDepartments(@RequestBody Map<String, Integer> request)
    {
        int page = request.getOrDefault("page", 0);
        int size = request.getOrDefault("size", 10);
      Integer branchid = request.getOrDefault("branchid", null);
//        Integer branchid = null; // Set this dynamically (null means it's not included)
//        if (branchid != null) {
//            request.put("branchid", branchid);
//        }
        Pageable pageable = PageRequest.of(page, size);
        return addDepartmentService.findAllDepartments(branchid, pageable);
    }

    @GetMapping("/ByDepartments/{departmentId}")
    public DepartmentListDTO ByDepartments(@PathVariable("departmentId") Integer departmentId)
    {
        return addDepartmentService.findByDepartmentId(departmentId);
    }

    @GetMapping("/DepartmentName")
    public DepartmentListDTO ByDepartmentName(@RequestParam("departmentName") String departmentName)
    {
        return addDepartmentService.findByDepartmentName(departmentName);
    }


}
