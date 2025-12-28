package com.example.booksapp.service;

import com.example.booksapp.model.Book;
import com.example.booksapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(@Autowired BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> fetchBookList() {
        return bookRepository.findAll();
    }

    @Override
    public Book fetchBookById(int bookId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        return optionalBook.orElse(null);

    }

    @Override
    public Book updateBook(Book book, int bookId) {
        Book bookDB = bookRepository.findById(bookId).orElse(null);
        if(bookDB == null){
            return null;
        }
        if(Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())){
            bookDB.setTitle(book.getTitle());
        }
        return bookRepository.save(bookDB);
    }

    @Override
    public boolean deleteBookById(Integer bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            bookRepository.deleteById(bookId);
            return true;
        }else{
            return false;
        }

    }
}
