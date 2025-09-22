package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.entity.StatusTable;
import com.thinkinnovative.library_management_system.repository.StatusTableRepository;
import com.thinkinnovative.library_management_system.service.StatusTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusTableServiceImpl implements StatusTableService {
    private final StatusTableRepository statusRepository;

    @Autowired
    public StatusTableServiceImpl(StatusTableRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public StatusTable addStatus(StatusTable statusTable) {
        return statusRepository.save(statusTable);
    }
}
