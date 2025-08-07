package com.thinkinnovative.payroll.repository;


import com.thinkinnovative.payroll.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Integer> {

    List<EmployeeSalary> findByEmpId(Integer empId);

    List<EmployeeSalary> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
