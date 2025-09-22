package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.entity.StatusTable;
import com.thinkinnovative.library_management_system.service.StatusTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusTableController {
    private final StatusTableService statusTableService;

    @Autowired
    public StatusTableController(StatusTableService statusTableService) {
        this.statusTableService = statusTableService;
    }

    // Endpoint to add a new status
    @PostMapping("/add")
    public StatusTable addStatus(@RequestBody StatusTable statusTable) {
        return statusTableService.addStatus(statusTable);
    }
}
