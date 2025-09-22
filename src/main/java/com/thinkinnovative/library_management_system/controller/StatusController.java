package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.entity.StatusInformation;
import com.thinkinnovative.library_management_system.service.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    StatusService service;
    public StatusController(StatusService service) {
        this.service = service;
    }
@PostMapping("/addStatus")
    public String addStatus(@RequestBody StatusInformation statusInformation) {

       return service.addStatus(statusInformation);
    }
    @GetMapping("/getStatus")
    public List<StatusInformation> getStatus()
    {
        return service.getStatus();
    }



}
