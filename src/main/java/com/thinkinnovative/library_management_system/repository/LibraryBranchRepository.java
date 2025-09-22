package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.LibraryBranchListDTO;
import com.thinkinnovative.library_management_system.entity.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Integer> {

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.LibraryBranchListDTO(" +
            "l.branchId, l.branchCode, l.branchName, l.branchAddress, l.openingTime, l.closingTime, l.hours, l.year) FROM LibraryBranch l")
    List<LibraryBranchListDTO> findAllBranch();

    LibraryBranch findByBranchName(String name);


    @Query("Select b.branchId From LibraryBranch b")
    List<Integer> findAllBranchIds();
}
