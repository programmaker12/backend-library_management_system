package com.thinkinnovative.payroll.service;

import com.thinkinnovative.payroll.entity.PayrollComponentRule;

public interface PayrollEstimationService {

    public Number payrollEstimation(Integer empId, PayrollComponentRule payrollComponentRule) ;
}
