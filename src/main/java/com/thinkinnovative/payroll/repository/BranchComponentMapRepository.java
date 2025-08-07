package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.BranchComponentMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BranchComponentMapRepository extends JpaRepository<BranchComponentMap, Integer> {

    // Custom query to retrieve all distinct branch IDs
    @Query("SELECT DISTINCT bcm.branchId FROM BranchComponentMap bcm")
    List<Integer> findDistinctBranchIds();

    // Find all records for a specific branch
    List<BranchComponentMap> findByBranchId(Integer branchId);

    // Find all records for a specific component
    List<BranchComponentMap> findByComponentId(Integer componentId);

    @Query("Select r.id from BranchComponentMap r where r.branchId = :branchId")
    List<Integer> findRuleComponentId(@Param("branchId") Integer branchId);

    @Query("Select r.componentId from BranchComponentMap r where r.id = :id")
    Integer retrieveComponentIdById(Integer id);
}

