package com.aspen.BookShelf.Buddy.Mapper;

import com.aspen.BookShelf.Buddy.Dto.Book.BookCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.Book.BookResponse;
import com.aspen.BookShelf.Buddy.Dto.Book.BookUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.Book;

import java.util.UUID;

public class BookMapper {

    public static Book toEntity(BookCreateRequest request){
        Book book = new Book();
        book.setGenre(request.getGenre());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPublishedDate(request.getPublishedDate());
        book.setPages(request.getPages());
        return book;
    }

    public static Book updateBook(BookUpdateRequest request, Book book){
        if(request.getAuthor()!=null&& request.getPages()!=null&&
                request.getGenre()!=null&& !request.getDescription().trim().isEmpty()&&
                !request.getTitle().trim().isEmpty()) {
            book.setTitle(request.getTitle());
            book.setGenre(request.getGenre());
            book.setAuthor(request.getAuthor());
            book.setDescription(request.getDescription());
            book.setPages(request.getPages());
            book.setPublishedDate(request.getPublishedDate());
        }
        return book;
    }

    public static BookResponse toResponse(Book book){
        BookResponse response = new BookResponse();
        response.setBookId(book.getBookId());
        response.setPublishedDate(book.getPublishedDate());
        response.setAuthor(book.getAuthor());
        response.setDescription(book.getDescription());
        response.setTitle(book.getTitle());
        response.setPages(book.getPages());
        response.setGenre(book.getGenre());
        return response;
    }

}
