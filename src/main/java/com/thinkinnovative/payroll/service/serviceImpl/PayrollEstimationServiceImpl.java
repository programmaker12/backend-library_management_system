package com.thinkinnovative.payroll.service.serviceImpl;

import com.thinkinnovative.payroll.entity.PayrollComponentRule;
import com.thinkinnovative.payroll.repository.EmployeeRepository;
import com.thinkinnovative.payroll.service.PayrollEstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PayrollEstimationServiceImpl implements PayrollEstimationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Number payrollEstimation(Integer empId, PayrollComponentRule payrollComponentRule) {
        Number estimatedAmount = BigDecimal.ZERO;

        switch (payrollComponentRule.getRuleType()) {
            case "percentage based" -> {
                BigDecimal baseAmount = employeeRepository.retrieveEmployeeSalaryById(empId); // e.g., ₹50,000
                if (baseAmount != null && payrollComponentRule.getPercentage() != null) {
                    BigDecimal percentage = payrollComponentRule.getPercentage();
                    estimatedAmount = baseAmount.multiply(percentage)
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP); // round to 2 decimal places
                }
                return estimatedAmount;
            }

            case "value based" -> {
                if (payrollComponentRule.getValue() != null) {
                    estimatedAmount = payrollComponentRule.getValue(); // Already BigDecimal
                }
                return estimatedAmount;
            }

            // Add more rule types if needed, e.g., KPI_BASED, SLAB_BASED

            default -> {
                return BigDecimal.ZERO;
            }
        }
    }
}
