package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Entity.Book;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book createBook(Book book){
        return bookRepo.save(book);
    }

    public Book getBookById(UUID id) throws ResourceNotFoundException {
        return bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find Book with this id: "+id));
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Book getBookByTitle(String title) throws ResourceNotFoundException{
        return bookRepo.findBookByTitle(title);
    }

    public Book getBookByAuthor(String author){
        return bookRepo.findBookByAuthor(author);
    }

    public Book getBookByGenre(String genre) {
        return bookRepo.findBookByGenre(genre);
    }

    public Book updateBook(UUID id,Book book) throws ResourceNotFoundException{
        Book book1 = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find Book with this id to update: "+id));
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setGenre(book.getGenre());
        bookRepo.save(book1);
        return book1;
    }

    public void deleteBook(UUID id){
        bookRepo.deleteById(id);
    }

}
