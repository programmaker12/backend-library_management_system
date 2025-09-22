package com.thinkinnovative.library_management_system.dto;

import com.thinkinnovative.library_management_system.entity.Queue;

public class BookDTO {
    private Integer bookID;
    private String title;
    private String author;
    private String genre;
    private Integer publishedYear;
    private Long statusId;   // Add statusId field
    private String statusName; // Add statusName field
    private Queue queue;
    private Integer stock;
    public Queue getInqueue() {
        return queue;
    }

    public void setInqueue(Queue inqueue) {
        this.queue = inqueue;
    }



    public BookDTO(Integer bookID, String title, String author, String genre, Integer publishedYear, Long statusId, String statusName) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.statusId = statusId;
        this.statusName = statusName;
       //this.queue = queue;
    }

    public BookDTO(Integer bookID, String title, String author, String genre, Integer publishedYear, Queue queue) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;

        this.queue = queue;
    }

    public BookDTO(Integer bookID, String title, String author, String genre, Integer publishedYear, Integer stock, Long statusId, String statusName) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.stock = stock;
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
