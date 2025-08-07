package com.thinkinnovative.payroll.schedular;

import com.thinkinnovative.library_management_system.repository.LibraryBranchRepository;
import com.thinkinnovative.payroll.entity.*;
import com.thinkinnovative.payroll.repository.*;
import com.thinkinnovative.payroll.service.PayrollEstimationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Component
@Slf4j
public class PayrollSchedular {

    @Autowired
    private PayrollEstimationService payrollEstimationService;

    @Autowired
    private LibraryBranchRepository libraryBranchRepository;

    @Autowired
    private BranchComponentMapRepository branchComponentMapRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayrollComponentRuleRepository payrollComponentRuleRepository;


    @Autowired
    private EmployeeComponentAmountRepository employeeComponentAmountRepository;

    @Autowired
    private PayrollComponentRepository payrollComponentRepository;

    @Autowired
    private PayrollSchedulerLogRepository payrollSchedulerLogRepository;

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeCustomComponentRepository employeeCustomComponentRepository;

    @Scheduled(cron = "0 30 17 21 * ?")
    public void payrollSchedular() {
        List<Integer> branchIds = libraryBranchRepository.findAllBranchIds();
        log.info("BranchIds : {}", branchIds);

        for (Integer branchId : branchIds) {
            List<Integer> ruleComponentId = branchComponentMapRepository.findRuleComponentId(branchId);
            log.info("ruleComponentId : {}", ruleComponentId);

            if (!ruleComponentId.isEmpty()) {
                List<Integer> empIds = employeeRepository.findAllEmpIds(branchId);
                log.info("empIds : {}", empIds);

                for (Integer empid : empIds) {
                    Integer designationId = employeeRepository.findDesignationIdByEmpId(empid);
                    BigDecimal salary = employeeRepository.findSalaryByEmpId(empid);
                    int income = 0;
                    int deduction = 0;
                    int customIncome = 0;
                    int customDeduction = 0;
                    for (Integer id : ruleComponentId) {
                        Number amount = 0;
                        Integer componentId = 0;
                        LocalDate date = LocalDate.now();


                        PayrollComponent componentCategory = payrollComponentRepository.retrieveComponentCategory(id);
                        if ("Basic Salary".equals(componentCategory.getComponentCategory())) {
                            amount = employeeRepository.retrieveEmployeeSalaryById(empid);
                            componentId = componentCategory.getId();
                        } else {
                            PayrollComponentRule payrollComponentRule = payrollComponentRuleRepository
                                    .retrieveByDesignationAndSalaryAndRuleComponentId(designationId, salary, id);

                            if (payrollComponentRule != null) {
                                amount = payrollEstimationService.payrollEstimation(empid, payrollComponentRule);
                                componentId = branchComponentMapRepository.retrieveComponentIdById(id);
                            } else {

                                String message = "Rule not configured for component ID: " + componentCategory.getId() +
                                        " and rule component ID: " + id;
                                saveLog(message);
                                continue; // skip saving EmployeeComponentAmount
                            }
                        }

                        EmployeeComponentAmount employeeComponentAmount = new EmployeeComponentAmount();
                        employeeComponentAmount.setEmployeeId(empid);
                        employeeComponentAmount.setComponentId(componentId);
                        employeeComponentAmount.setAmount(new BigDecimal(amount.toString()));
                        employeeComponentAmount.setDate(date);
                        employeeComponentAmountRepository.save(employeeComponentAmount);
                        //Income and Deduction
                        if (Objects.equals(componentCategory.getComponentType(), "Income")) {
                            income +=  amount.intValue();
                        } else {
                            deduction += amount.intValue();
                        }

                    }

                    List<EmployeeCustomComponent> customComponents = employeeCustomComponentRepository.findByEmpId(empid);
                    for (EmployeeCustomComponent employeecomponent : customComponents) {
                        if(Objects.equals(employeecomponent.getType(), "credit")) {
                            customIncome = employeecomponent.getAmount().intValue();
                        } else {
                            customDeduction = employeecomponent.getAmount().intValue();
                        }
                    }
                    EmployeeComponentAmount employeeComponentAmount = new EmployeeComponentAmount();
                    employeeComponentAmount.setEmployeeId(empid);
                    employeeComponentAmount.setComponentId(17);
                    employeeComponentAmount.setAmount(new BigDecimal(customIncome));
                    employeeComponentAmount.setDate(LocalDate.now());

                    EmployeeComponentAmount employeeComponentAmount1 = new EmployeeComponentAmount();
                    employeeComponentAmount1.setEmployeeId(empid);
                    employeeComponentAmount1.setComponentId(18);
                    employeeComponentAmount1.setAmount(new BigDecimal(customDeduction));
                    employeeComponentAmount1.setDate(LocalDate.now());

                    EmployeeSalary employeeSalary = new EmployeeSalary();
                    employeeSalary.setGrossSalary(BigDecimal.valueOf(income));
                    employeeSalary.setDeductions(BigDecimal.valueOf(deduction));
                    employeeSalary.setNetValue(BigDecimal.valueOf(income-deduction));
                    employeeSalaryRepository.save(employeeSalary);


                }

            } else {
                String message = "No components configured for branch ID: " + branchId;
                saveLog(message);
            }
        }
    }

    // Utility method to save logs
    private void saveLog(String message) {
        PayrollSchedulerLog logEntry = new PayrollSchedulerLog();
        logEntry.setMessage(message);
        logEntry.setLogDate(LocalDateTime.now());
        payrollSchedulerLogRepository.save(logEntry);
    }


//    private PayrollSchedulerLogRepository payrollSchedulerLogRepository;
//
//    @Scheduled(cron = "0 30 17 21 * ?")
//    public void payrollSchedular() {
//
//        List<Integer> branchIds = libraryBranchRepository.findAllBranchIds();
//        log.info("BranchIds : {}", branchIds);
//
//        for(Integer branchId : branchIds) {
//            List<Integer> ruleComponentId = branchComponentMapRepository.findRuleComponentId(branchId);
//            log.info("ruleComponentId : {}", ruleComponentId);
//            if (!ruleComponentId.isEmpty()) {
//                List<Integer> empIds = employeeRepository.findAllEmpIds(branchId);
//                log.info("empIds : {}", empIds);
//                for (Integer empid : empIds) {
//                    Integer designationId = employeeRepository.findDesignationIdByEmpId(empid);
//                    BigDecimal salary = employeeRepository.findSalaryByEmpId(empid);
//                    for (Integer id : ruleComponentId) {
//                        Number amount = 0;
//                        Integer componentId = 0;
//                        LocalDate date = LocalDate.now();
//                        PayrollComponent componentCategory = payrollComponentRepository.retrieveComponentCategory(id);
//                        log.info(String.valueOf(componentCategory));
//                        if (Objects.equals(componentCategory.getComponentCategory(), "Basic Salary")) {
//                            amount = employeeRepository.retrieveEmployeeSalaryById(empid);
//                            componentId = componentCategory.getId();
//                            date = LocalDate.now();
//
//                        } else {
//                            PayrollComponentRule payrollComponentRule = payrollComponentRuleRepository.retrieveByDesignationAndSalaryAndRuleComponentId(designationId, salary, id);
//                            if (payrollComponentRule != null) {
//                                amount = payrollEstimationService.payrollEstimation(empid, payrollComponentRule);
//                                componentId = branchComponentMapRepository.retrieveComponentIdById(id);
//                                date = LocalDate.now();
//                            } else {
//                                String message = "The rule is not configured for component id : " + componentCategory.getId() + " and for rule component id : " + id;
//                                PayrollSchedulerLog payrollSchedulerLog = new PayrollSchedulerLog();
//                                payrollSchedulerLog.setMessage(message);
//                                payrollSchedulerLog.setLogDate(LocalDateTime.now());
//                                payrollSchedulerLogRepository.save(payrollSchedulerLog);
//
//                            }
//                        }
//                        EmployeeComponentAmount employeeComponentAmount = new EmployeeComponentAmount();
//                        employeeComponentAmount.setEmployeeId(empid);
//                        employeeComponentAmount.setComponentId(componentId);
//                        employeeComponentAmount.setAmount(new BigDecimal(amount.toString()));
//                        employeeComponentAmount.setDate(date);
//                        employeeComponentAmountRepository.save(employeeComponentAmount);
//
//
//                    }
//
//
//                }
//            } else {
//                String message = "The components are not configured for branch id : " + branchId;
//                PayrollSchedulerLog payrollSchedulerLog = new PayrollSchedulerLog();
//                payrollSchedulerLog.setMessage(message);
//                payrollSchedulerLog.setLogDate(LocalDateTime.now());
//                payrollSchedulerLogRepository.save(payrollSchedulerLog);
//            }
//        }
//
//
//
//    }
}
