package com.thinkinnovative.payroll.service.serviceImpl;

import com.thinkinnovative.payroll.entity.Employee;
import com.thinkinnovative.payroll.repository.EmployeeRepository;
import com.thinkinnovative.library_management_system.repository.DepartmentRepository;
import com.thinkinnovative.library_management_system.repository.LibraryBranchRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LibraryBranchRepository libraryBranchRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    public String parse1Excel(InputStream inputStream) throws IOException {
        List<Employee> employeeList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over each row
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Check if row is empty
                boolean isRowEmpty = true;
                for (Cell cell : row) {
                    String cellValue = getCellValue(cell);
                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        isRowEmpty = false;
                        break;
                    }
                }
                if (isRowEmpty) break; // Stop processing at the first empty row

                // Parse the row into MemberInfoDTO
                Employee employeeRequestDto = new Employee();

                int counter = 0;
                employeeRequestDto.setFirstName(getCellValue(row.getCell(counter++)));
                employeeRequestDto.setLastName(getCellValue(row.getCell(counter++)));
                employeeRequestDto.setEmail(getCellValue(row.getCell(counter++)));
                employeeRequestDto.setHireDate(LocalDate.parse(getCellValue(row.getCell(counter++))));
                employeeRequestDto.setSalary(new BigDecimal(getCellValue(row.getCell(counter++))));
                employeeRequestDto.setDepartmentId(departmentRepository.findbyDepartmentName(getCellValue(row.getCell(counter++))).getDepartmentId());
                employeeRequestDto.setActive(Boolean.parseBoolean(getCellValue(row.getCell(counter++))));
                employeeRequestDto.setBranchId(libraryBranchRepository.findByBranchName(getCellValue(row.getCell(counter++))).getBranchId());
                employeeList.add(employeeRequestDto);
            }
        }
        employeeRepository.saveAll(employeeList);
        return "The data uploaded successfully";
    }
    public String parseExcel(InputStream inputStream) throws IOException {
        int chunkSize = 10;
        List<Employee> batch = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Check if the row is empty
                boolean isRowEmpty = true;
                for (Cell cell : row) {
                    String cellValue = getCellValue(cell);
                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        isRowEmpty = false;
                        break;
                    }
                }
                if (isRowEmpty) break;

                // Build Employee from row
                Employee employee = new Employee();
                int counter = 0;

                employee.setFirstName(getCellValue(row.getCell(counter++)));
                employee.setLastName(getCellValue(row.getCell(counter++)));
                employee.setEmail(getCellValue(row.getCell(counter++)));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(getCellValue(row.getCell(counter++)), formatter);
                LocalDate date = zonedDateTime.toLocalDate();

                employee.setHireDate(date);
                employee.setSalary(new BigDecimal(getCellValue(row.getCell(counter++))));
                employee.setDepartmentId(departmentRepository
                        .findbyDepartmentName(getCellValue(row.getCell(counter++)))
                        .getDepartmentId());
                employee.setActive(Boolean.parseBoolean(getCellValue(row.getCell(counter++))));
                employee.setBranchId(libraryBranchRepository
                        .findByBranchName(getCellValue(row.getCell(counter++)))
                        .getBranchId());

                batch.add(employee);

                // Save in chunks
                if (batch.size() == chunkSize) {
                    employeeRepository.saveAll(batch);
                    batch.clear(); // release memory
                }
            }

            // Save remaining records
            if (!batch.isEmpty()) {
                employeeRepository.saveAll(batch);
            }
        }

        return "Employees uploaded in chunks successfully.";
    }


    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
