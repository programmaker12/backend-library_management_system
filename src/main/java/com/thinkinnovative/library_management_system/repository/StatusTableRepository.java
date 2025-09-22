package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.entity.StatusTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTableRepository extends JpaRepository<StatusTable, Long> {

}
