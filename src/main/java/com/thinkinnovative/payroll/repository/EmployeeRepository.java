package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom finder methods (optional):
    Employee findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("Select e.id from Employee e where e.branchId = :branchId")
    List<Integer> findAllEmpIds(Integer branchId);

    @Query("Select e.salary from Employee e where e.id = :empId")
    BigDecimal findSalaryByEmpId(Integer empId);

    @Query("Select e.designationId from Employee e where e.id = :empId")
    Integer findDesignationIdByEmpId(Integer empId);

    @Query("Select e.salary from Employee e where e.id = :empId")
    BigDecimal retrieveEmployeeSalaryById(Integer empId);

}
