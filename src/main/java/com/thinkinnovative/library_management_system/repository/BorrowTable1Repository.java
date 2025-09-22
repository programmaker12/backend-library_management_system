package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.BorrowTable1DTO;
import com.thinkinnovative.library_management_system.entity.BorrowTable1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowTable1Repository extends JpaRepository<BorrowTable1, Integer> {

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.BorrowTable1DTO(" +
            "b.book.bookID,  b.book.title, b.borrowDate) "  +
                    "FROM BorrowTable1 b " +
            "WHERE b.member.memberID = :memberID")
    List<BorrowTable1DTO> findBooksBorrowedByMember(@Param("memberID") Integer memberID);

    @Query("SELECT b FROM BorrowTable1 b WHERE b.book.bookID = :bookId AND b.member.memberID = :memberId AND b.returnDate IS NULL")
    BorrowTable1 findByBookIdAndMemberId(@Param("bookId") int bookId, @Param("memberId") int memberId);

}
