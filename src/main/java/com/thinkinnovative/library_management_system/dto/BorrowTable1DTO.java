package com.thinkinnovative.library_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowTable1DTO implements Serializable {

    @NotNull(message = "Book ID cannot be null")
    private Integer bookId;

    @Size(max = 255, message = "Book name must be less than 255 characters")
    private String bookName;

    @NotNull(message = "Member ID cannot be null")
    private Integer memberId;

    @Size(max = 255, message = "Member name must be less than 255 characters")
    private String memberName;

    private LocalDate date;

    // Optional: Custom constructor for specific needs
    public BorrowTable1DTO(Integer bookId, String bookName, LocalDate date) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.date = date;
    }

    // Optional: If you want to hide memberId in some cases
    // @JsonIgnore
    // private Integer memberId;
}
