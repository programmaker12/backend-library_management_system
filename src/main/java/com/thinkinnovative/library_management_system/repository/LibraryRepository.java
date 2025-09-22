package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.BookDTO;
import com.thinkinnovative.library_management_system.entity.LibraryInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibraryRepository extends JpaRepository <LibraryInformation, Long> {
    @Query("SELECT new com.thinkinnovative.library_management_system.dto.BookDTO(" +
            "l.bookID, l.title, l.author, l.genre, l.publishedYear, " +
            "s.statusID, s.statusName) " +   // Perform JOIN on status_id
            "FROM LibraryInformation l " +
            "JOIN l.status s")  // JOIN with the StatusTable
    List<BookDTO> findAllBooksWithStatus();

    @Query("SELECT l FROM LibraryInformation l WHERE l.bookID = :bookID")
    LibraryInformation findBook1ById(Integer bookID);


    @Query("SELECT new com.thinkinnovative.library_management_system.dto.BookDTO(" +
            "l.bookID, l.title, l.author, l.genre, l.publishedYear,"+" s.statusID, s.statusName) " +
            "FROM LibraryInformation l " +
            "JOIN l.status s WHERE l.bookID = :bookID")
    BookDTO findBookById(@Param("bookID") Integer bookID);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.BookDTO(" +
            "l.bookID, l.title, l.author, l.genre, l.publishedYear,l.inqueue ) " +
            "FROM LibraryInformation l " +
            "WHERE l.bookID = :bookID")
    LibraryInformation findBookQueueById(@Param("bookID") Integer bookID);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.BookDTO(" +
            "l.bookID, l.title, l.author, l.genre, l.publishedYear, " +
            "s.statusID, s.statusName) " +   // Perform JOIN on status_id
            "FROM LibraryInformation l " +
            "JOIN l.status s")
    Page<BookDTO> getBookByPage(Pageable pageable);







//    @Modifying
//    @Query(value = "INSERT INTO library_information (title, author, genre, published_year, status_id) " +
//            "SELECT :title, :author, :genre, :publishedYear, s.statusID " +
//            "FROM status_table s WHERE s.statusID = :statusID", nativeQuery = true)
//    void insertBookWithStatusNative(@Param("title") String title,
//                              @Param("author") String author,
//                              @Param("genre") String genre,
//                              @Param("publishedYear") Integer publishedYear,
//                              @Param("statusID") Long statusID);



}
