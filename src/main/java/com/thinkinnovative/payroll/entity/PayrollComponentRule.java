package com.thinkinnovative.payroll.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payroll_component_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayrollComponentRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Assuming designation refers to a designation ID
    @Column(name = "designation")
    private Integer designation;

    @Column(name = "min_salary_range", precision = 10, scale = 2)
    private BigDecimal minSalaryRange;

    @Column(name = "max_salary_range", precision = 10, scale = 2)
    private BigDecimal maxSalaryRange;

    @Column(name = "activate_date")
    private LocalDate activateDate;

    @Column(name = "deactivate_date")
    private LocalDate deactivateDate;

    @Column(name = "rule_type")
    private String ruleType;

    @Column(name = "value", precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "kpi_based_rule", precision = 10, scale = 2)
    private BigDecimal kpiBasedRule;

    @Column(name = "slab_based_id")
    private Integer slabBasedId;

    @Column(name = "rule_component_id")
    private Integer ruleComponentId;

//    // Enum for rule_type
//    public enum RuleType {
//        @Enumerated(EnumType.STRING)
//        @Column(name = "rule_type")
//        VALUE_BASED("value based"),
//        PERCENTAGE_BASED("percentage based"),
//        KPI_BASED("KPI based"),
//        SLAB_BASED("slab based");
//
//        private final String label;
//
//        RuleType(String label) {
//            this.label = label;
//        }

//        @Override
//        public String toString() {
//            return label;
//        }
    //}
}
