package com.thinkinnovative.library_management_system;

import com.thinkinnovative.payroll.schedular.PayrollSchedular;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {
		"com.thinkinnovative.library_management_system",
		"com.thinkinnovative.payroll"
})
@EnableJpaRepositories(basePackages = {
		"com.thinkinnovative.library_management_system.repository",
		"com.thinkinnovative.payroll.repository"
})
@EntityScan(basePackages = {
		"com.thinkinnovative.library_management_system.entity",
		"com.thinkinnovative.payroll.entity"
})
public class DemoGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGradleApplication.class, args);
		System.out.println("I have started my first project");
	}

	// ✅ Move this outside main() method
//	@Bean
//	public CommandLineRunner runManually(PayrollSchedular payrollSchedular) {
//		return args -> payrollSchedular.payrollSchedular(); // Spring will inject this bean
//	}
}
