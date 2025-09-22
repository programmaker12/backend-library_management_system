package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.LibraryBranchListDTO;
import com.thinkinnovative.library_management_system.repository.LibraryBranchRepository;
import com.thinkinnovative.library_management_system.service.LibraryBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LibraryBranchServiceImpl implements LibraryBranchService {

   private final LibraryBranchRepository libraryBranchRepository;

    @Override
    public List<LibraryBranchListDTO> listBranch() {
        return libraryBranchRepository.findAllBranch();
    }
}
