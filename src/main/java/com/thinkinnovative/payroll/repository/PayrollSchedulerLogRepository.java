package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.PayrollSchedulerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollSchedulerLogRepository extends JpaRepository<PayrollSchedulerLog, Integer> {

    // Optional: useful methods to filter logs

}
