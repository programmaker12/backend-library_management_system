package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.EmployeeComponentAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeComponentAmountRepository extends JpaRepository<EmployeeComponentAmount, Integer> {

    // Example custom query methods:

    List<EmployeeComponentAmount> findByEmployeeId(Integer employeeId);

    List<EmployeeComponentAmount> findByComponentId(Integer componentId);

    List<EmployeeComponentAmount> findByEmployeeIdAndDate(Integer employeeId, LocalDate date);

    List<EmployeeComponentAmount> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
