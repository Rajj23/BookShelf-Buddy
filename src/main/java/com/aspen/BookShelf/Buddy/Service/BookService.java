package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Dto.Book.BookCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.Book.BookResponse;
import com.aspen.BookShelf.Buddy.Dto.Book.BookUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.Book;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Mapper.BookMapper;
import com.aspen.BookShelf.Buddy.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public BookResponse createBook(BookCreateRequest request){
        Book book = BookMapper.toEntity(request);
        return BookMapper.toResponse(bookRepo.save(book));
    }

    public BookResponse getBookById(UUID id) throws ResourceNotFoundException {
        Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find Book with this id: "+id));
        return BookMapper.toResponse(book);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepo.findAll().stream().map(BookMapper::toResponse).collect(Collectors.toList());
    }

    public BookResponse getBookByTitle(String title) throws ResourceNotFoundException{
        Book book =  bookRepo.findBookByTitle(title);
        return BookMapper.toResponse(book);
    }

    public BookResponse getBookByAuthor(String author){
        Book book =  bookRepo.findBookByAuthor(author);
        return BookMapper.toResponse(book);
    }

    public BookResponse getBookByGenre(String genre) {
        Book book =  bookRepo.findBookByGenre(genre);
        return BookMapper.toResponse(book);
    }

    public BookResponse updateBook(BookUpdateRequest request) throws ResourceNotFoundException{
        Book book = bookRepo.findById(request.getBookId()).
                orElseThrow(()->new ResourceNotFoundException("Can't find Book with this id to update: "+request.getBookId()));
       Book updatedBook = BookMapper.updateBook(request,book);
       return BookMapper.toResponse(bookRepo.save(updatedBook));
    }

    public void deleteBook(UUID id){
        bookRepo.deleteById(id);
    }

}
