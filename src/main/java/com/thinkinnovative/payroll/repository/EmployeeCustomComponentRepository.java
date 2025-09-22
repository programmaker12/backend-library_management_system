package com.thinkinnovative.payroll.repository;


import com.thinkinnovative.payroll.entity.EmployeeCustomComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCustomComponentRepository extends JpaRepository<EmployeeCustomComponent, Integer> {

    List<EmployeeCustomComponent> findByEmpId(Integer empId);

    List<EmployeeCustomComponent> findByPaidStatus(String paidStatus);

    List<EmployeeCustomComponent> findByRequestStatus(String requestStatus);
}
