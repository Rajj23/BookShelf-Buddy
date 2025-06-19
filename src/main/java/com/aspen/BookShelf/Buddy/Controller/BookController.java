package com.aspen.BookShelf.Buddy.Controller;

import com.aspen.BookShelf.Buddy.Entity.Book;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return new  ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/get/titles/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.OK);
    }

    @GetMapping("/get/author/{author}")
    public ResponseEntity<Book> getBookByAuthor(@PathVariable String author){
        return new ResponseEntity<>(bookService.getBookByAuthor(author),HttpStatus.OK);
    }

    @GetMapping("/get/genre/{genre}")
    public ResponseEntity<Book> getBookByGenre(@PathVariable String genre){
        return new ResponseEntity<>(bookService.getBookByGenre(genre),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id,@RequestBody Book book) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.updateBook(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
         bookService.deleteBook(id);
         return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

}
