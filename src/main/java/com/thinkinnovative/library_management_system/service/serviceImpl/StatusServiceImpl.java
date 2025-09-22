package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.entity.StatusInformation;
import com.thinkinnovative.library_management_system.repository.StatusRepository;
import com.thinkinnovative.library_management_system.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Override
    public String addStatus(StatusInformation statusInformation) {
        statusRepository.save(statusInformation);
        return "Status added successfully";
    }

    @Override
    public List<StatusInformation>getStatus() {
        return statusRepository.findAll();
    }
}
