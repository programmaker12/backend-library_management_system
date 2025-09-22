package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.BookDTO;
import com.thinkinnovative.library_management_system.dto.BookRequestDTO;
import com.thinkinnovative.library_management_system.entity.LibraryInformation;
import com.thinkinnovative.library_management_system.entity.StatusTable;
import com.thinkinnovative.library_management_system.repository.LibraryRepository;
import com.thinkinnovative.library_management_system.repository.StatusTableRepository;
import com.thinkinnovative.library_management_system.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class LibraryController  {


    private final LibraryService libraryService;
    private final StatusTableRepository statusTableRepository;
    private final LibraryRepository libraryRepository;
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    public LibraryController(LibraryService libraryService, StatusTableRepository statusTableRepository, LibraryRepository libraryRepository) {
        this.libraryService = libraryService;
        this.statusTableRepository = statusTableRepository;
        this.libraryRepository = libraryRepository;
    }


    @PostMapping("/addBook")
    public ResponseEntity<String> addBooks(@RequestBody BookRequestDTO bookRequestDTO)
    {
        logger.info("Payload: {}", bookRequestDTO);
        Long statusid = bookRequestDTO.getStatusId();
        if (statusid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
        }
        Long a = bookRequestDTO.getStatusId();
        StatusTable status = statusTableRepository.findById(a)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));
        LibraryInformation libraryInformation = new LibraryInformation();
        libraryInformation.setStatus(status);
        libraryInformation.setAuthor(bookRequestDTO.getAuthor());
        libraryInformation.setGenre(bookRequestDTO.getGenre());
        libraryInformation.setTitle(bookRequestDTO.getTitle());
        libraryInformation.setPublishedYear(bookRequestDTO.getPublishedYear());
        libraryInformation.setStock(bookRequestDTO.getStock());
        libraryInformation.setStock(bookRequestDTO.getStock());
        libraryRepository.save(libraryInformation);
        return ResponseEntity.ok("Book added successfully");
    }
//    @PostMapping("/addBook")
//    public ResponseEntity<String> addBook(@RequestBody LibraryInformation libraryInformation) {
//        logger.info("Payload: {}", libraryInformation);
//
//        Long statusId = libraryInformation.getStatus().getStatusID();  // Get statusID
//
//        if (statusId == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
//        }
//
//        // Using custom query to insert book with statusID
////        libraryRepository.insertBookWithStatusNative(
////                libraryInformation.getTitle(),
////                libraryInformation.getAuthor(),
////                libraryInformation.getGenre(),
////                libraryInformation.getPublishedYear(),
////                statusId
////        );
////
////        return ResponseEntity.ok("Book added successfully");
////    }


//    public ResponseEntity<String> add1Book(@RequestBody LibraryInformation libraryInformation)
//    {
//
//        return ResponseEntity.ok("Book added successfully");
//    }


    @PostMapping("/saveAllBook")
    public String allBook(@RequestBody List<LibraryInformation> libraryInformation)
    {

        return libraryService.saveBooks(libraryInformation);
    }
    @GetMapping("/getAllBooks")
    public List<LibraryInformation> allBooks()
    {

        return libraryService.getAllBooks();
    }

    @GetMapping("/getAllBooksWithStatus")
    public List<BookDTO> getAllBooksWithStatus() {
        List<BookDTO> books = libraryService.getAllBooksWithStatus();
        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found with status");
        }
        System.out.println("Response"+books);
        return books;
       // return libraryService.getAllBooksWithStatus();
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<?>getBookById(@PathVariable Integer id) {

       if (id == null || id <= 0) {
           throw new IllegalArgumentException("Invalid book id");
       }
       BookDTO book = libraryService.getBookById(id);
       if (book == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
       }

       return ResponseEntity.ok(book);


    }

    @PostMapping("/ByPage")
    public Page<BookDTO> getBookByPage(@RequestBody Map<String, Integer> request)
    {
        int offset = request.get("offset");
        int limit = request.get("limit");
        return libraryService.getBookByPage(offset, limit);
    }

    @GetMapping("/books/titles")
    public List<String> getBookTitles() {
        return libraryService.getAllBookTitles();
    }
    @GetMapping("/books/{author}/author")
    public List<BookDTO> getBookByAuthor(@PathVariable String author) {

    return libraryService.getAllBookByAuthor(author);
    }
    @GetMapping("/books/author")
    public List<Object> getBookWithAvailableStock() {

        return libraryService.getAllBooksWithAvailableStock();
    }


}
