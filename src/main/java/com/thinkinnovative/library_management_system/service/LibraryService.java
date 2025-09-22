package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.BookDTO;
import com.thinkinnovative.library_management_system.entity.LibraryInformation;
import org.springframework.data.domain.Page;

import java.util.List;


public interface LibraryService {

    public String addBooks(LibraryInformation libraryInformation);
    //public String addBook(LibraryInformation libraryInformation);
    //public String getBooksById(Integer Id);
    public List<LibraryInformation> getAllBooks();
//    public String updateBooks(LibraryInformation libraryInformation);
//    public String modifyStatus();
    public String saveBooks( List<LibraryInformation> libraryInformation);
    public List<BookDTO> getAllBooksWithStatus();
    public BookDTO getBookById(Integer id);
    public Page<BookDTO>getBookByPage(int page, int size);
    public List<String> getAllBookTitles();
    public List<BookDTO> getAllBookByAuthor(String author);
    public List<Object> getAllBooksWithAvailableStock();
}
