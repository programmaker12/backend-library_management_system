package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.QueueDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QueueService {

    public CompletableFuture<String> addQueue(Integer bookId, Integer memberId);
    public List<QueueDTO> queueByBookid(Integer bookId);
}
