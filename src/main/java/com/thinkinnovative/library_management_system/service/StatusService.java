package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.entity.StatusInformation;

import java.util.List;

public interface StatusService {
    public String addStatus(StatusInformation statusInformation);
    public List<StatusInformation> getStatus();
}
