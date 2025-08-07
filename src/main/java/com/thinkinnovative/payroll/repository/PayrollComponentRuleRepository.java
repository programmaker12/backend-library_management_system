package com.thinkinnovative.payroll.repository;

import com.thinkinnovative.payroll.entity.PayrollComponentRule;
//import com.thinkinnovative.payroll.entity.PayrollComponentRule.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollComponentRuleRepository extends JpaRepository<PayrollComponentRule, Integer> {

    // Find all rules for a specific designation
    List<PayrollComponentRule> findByDesignation(Integer designation);

    // Find rules by rule type (value based, percentage based, etc.)
    List<PayrollComponentRule> findByRuleType(String ruleType);

    // Find rules for a specific component
    List<PayrollComponentRule> findByComponentId(Integer componentId);

    // Find rules active on a specific date
    List<PayrollComponentRule> findByActivateDateLessThanEqualAndDeactivateDateGreaterThanEqual(LocalDate start, LocalDate end);

    // Custom query if you want to filter by salary range
    List<PayrollComponentRule> findByMinSalaryRangeLessThanEqualAndMaxSalaryRangeGreaterThanEqual(BigDecimal salary1, BigDecimal salary2);
    // Find rule based on designation, salary and rule_component_id
    @Query("Select r from PayrollComponentRule r " +
           "where designation = :designationId " +
            " and :amount between minSalaryRange and maxSalaryRange " +
            " and ruleComponentId = :id ")
    PayrollComponentRule retrieveByDesignationAndSalaryAndRuleComponentId(@Param("designationId") Integer designationId, @Param("amount") BigDecimal amount, @Param("id") Integer id);
}
