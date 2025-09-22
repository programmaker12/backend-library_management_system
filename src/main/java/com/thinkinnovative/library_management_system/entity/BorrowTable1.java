package com.thinkinnovative.library_management_system.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_table1")
public class BorrowTable1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer borrowid;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookid")
    private LibraryInformation book;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberID")
    private MemberTable member;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public MemberTable getMember() {
        return member;
    }

    public void setMember(MemberTable member) {
        this.member = member;
    }

    public LibraryInformation getBook() {
        return book;
    }

    public void setBook(LibraryInformation book) {
        this.book = book;
    }

    public Integer getBorrowID() {
        return borrowid;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowid = borrowID;
    }



    // Getters and Setters
}



