package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.BorrowTable1DTO;
import com.thinkinnovative.library_management_system.entity.BorrowTable1;
import com.thinkinnovative.library_management_system.entity.LibraryInformation;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import com.thinkinnovative.library_management_system.repository.BorrowTable1Repository;
import com.thinkinnovative.library_management_system.repository.LibraryRepository;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import com.thinkinnovative.library_management_system.service.BorrowTable1Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowTable1ServiceImpl implements BorrowTable1Service {

    private final LibraryRepository libraryRepository;
    private final BorrowTable1Repository borrowTable1Repository;
    private final MemberRepository memberRepository;
    private final QueueServiceImpl queueService;

    @Override
    public String borrowBook(BorrowTable1DTO dto) {
        try {
            Optional<LibraryInformation> libraryOpt = libraryRepository.findById(Long.valueOf(dto.getBookId()));
            if (libraryOpt.isEmpty()) {
                return "Book with ID " + dto.getBookId() + " not found.";
            }

            LibraryInformation book = libraryOpt.get();
            if (book.getStock() <= 0) {
                String queueMessage = queueService.addQueue(dto.getBookId(), dto.getMemberId()).get();
                return "The book is out of stock. " + queueMessage;
            }

            Optional<MemberTable> memberOpt = memberRepository.findById(dto.getMemberId());
            if (memberOpt.isEmpty()) {
                return "Member with ID " + dto.getMemberId() + " not found.";
            }

            // Create and save borrow entry
            BorrowTable1 borrowEntry = new BorrowTable1();
            borrowEntry.setBook(book);
            borrowEntry.setMember(memberOpt.get());
            borrowEntry.setBorrowDate(LocalDate.now());

            borrowTable1Repository.save(borrowEntry);

            // Update stock
            book.setStock(book.getStock() - 1);
            libraryRepository.save(book);

            return "The book has been borrowed successfully.";

        } catch (Exception e) {
            log.error("Error borrowing book", e);
            return "An error occurred while borrowing the book. Please try again.";
        }
    }

    @Override
    public List<BorrowTable1DTO> borrowBookByMember(Integer memberId) {
        return borrowTable1Repository.findBooksBorrowedByMember(memberId);
    }

    @Override
    public BorrowTable1 findBooksByBookidAndMemberid(Integer bookId, Integer memberId) {
        return borrowTable1Repository.findByBookIdAndMemberId(bookId, memberId);
    }

    @Override
    public String returnBook(BorrowTable1 borrowEntry) {
        borrowEntry.setReturnDate(LocalDate.now());

        LibraryInformation book = borrowEntry.getBook();
        book.setStock(book.getStock() + 1);
        libraryRepository.save(book);
        borrowTable1Repository.save(borrowEntry);

        long borrowedDays = ChronoUnit.DAYS.between(borrowEntry.getBorrowDate(), LocalDate.now());
        int allowedDays = 14;
        double lateFee = borrowedDays > allowedDays ? (borrowedDays - allowedDays) * 2.0 : 0.0;

        return String.format("The book has been returned successfully.%s",
                lateFee > 0 ? " Please pay the late fee: " + lateFee : "");
    }
}
