package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.MemberInfoDTO;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMembersExcel(InputStream inputStream) throws IOException {
        List<MemberInfoDTO> members = parseExcel(inputStream);
        for (MemberInfoDTO member : members) {
            createMemberFromExcel(member);
        }
    }

    public List<MemberInfoDTO> parseExcel(InputStream inputStream) throws IOException {
        List<MemberInfoDTO> memberList = new ArrayList<>();

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
                MemberInfoDTO memberInfoDTO = new MemberInfoDTO();

                int counter = 0;
                memberInfoDTO.setMemberName(getCellValue(row.getCell(counter++)));
                memberInfoDTO.setEmail(getCellValue(row.getCell(counter++)));

                memberList.add(memberInfoDTO);
            }
        }
        return memberList;
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

    public String createMemberFromExcel(MemberInfoDTO member) {
        if (member.getMemberName() == null || member.getEmail() == null) {
            return "Invalid member data from Excel";
        }

        MemberTable member1 = new MemberTable();
        member1.setMemberName(member.getMemberName());
        member1.setEmail(member.getEmail());
        member1.setMembershipDate(LocalDate.now());
        memberRepository.save(member1);
        return "The member from Excel was created successfully.";
    }
}
