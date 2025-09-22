package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.BorrowTable1DTO;
import com.thinkinnovative.library_management_system.entity.BorrowTable1;

import java.util.List;

public interface BorrowTable1Service {
    public String borrowBook(BorrowTable1DTO borrowTable1DTO); // this method will map the book and the member
    public List<BorrowTable1DTO> borrowBookByMember(Integer memberID);
    public BorrowTable1 findBooksByBookidAndMemberid(Integer bookid, Integer memberId);
    public String returnBook(BorrowTable1 borrowTable1);
}
