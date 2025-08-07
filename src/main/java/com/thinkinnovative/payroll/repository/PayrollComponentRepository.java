package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.PayrollComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollComponentRepository extends JpaRepository<PayrollComponent, Integer> {

    // Custom query methods (optional)

    List<PayrollComponent> findByComponentType(String componentType);

    List<PayrollComponent> findByComponentCategory(String componentCategory);

    List<PayrollComponent> findByComponentTypeAndComponentCategory(String componentType, String componentCategory);

    @Query("SELECT p FROM PayrollComponent p " +
            "JOIN BranchComponentMap b ON b.componentId = p.id " +
            "WHERE b.id = :id")
    PayrollComponent retrieveComponentCategory(@Param("id") Integer id);

}
