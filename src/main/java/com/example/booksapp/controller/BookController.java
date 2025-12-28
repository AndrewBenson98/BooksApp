package com.example.booksapp.controller;

import com.example.booksapp.model.Book;
import com.example.booksapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class BookController {

    private final BookService bookService;


    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        Book book1 = bookService.saveBook(book);
        return new ResponseEntity<>(book1,HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> fetchBookList(){
        List<Book> bookList =bookService.fetchBookList();
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> fetchBookById(@PathVariable("id") int bookId){
        Book book1 = bookService.fetchBookById(bookId);
        if(book1 == null){
            return new ResponseEntity<>(book1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book1,HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int bookId){
        Book book1 = bookService.updateBook(book, bookId);
        if(book1 == null){
            return new ResponseEntity<>(book1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book1,HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") int bookId){
        if(bookService.deleteBookById(bookId)){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Book Not Found",HttpStatus.NOT_FOUND);
        }
    }

}
