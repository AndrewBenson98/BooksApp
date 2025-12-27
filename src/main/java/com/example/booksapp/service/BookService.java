package com.example.booksapp.service;

import com.example.booksapp.model.Book;

import java.util.List;

public interface BookService {

    //Save a new book
    Book saveBook(Book book);

    //Get a list of all books
    List<Book> fetchBookList();

    //Get Single book
    Book fetchBookById(int bookId);

    //Update a book
    Book updateBook(Book book, int bookId);

    //Delete a book
    boolean deleteBookById(Integer bookId);


}
