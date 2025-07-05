package com.aspen.BookShelf.Buddy.Mapper;

import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryResponse;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.Book;
import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.ReadingStatus;
import com.aspen.BookShelf.Buddy.Entity.User;

public class BookEntryMapper {

    public static BookEntry toEntity(BookEntryCreateRequest request, User user, Book book){
        BookEntry entry = new BookEntry();
        entry.setUser(user);
        entry.setBook(book);
        entry.setStatus(ReadingStatus.valueOf(request.getStatus()));
        entry.setStartedOn(request.getStartedOn());
        return entry;
    }

    public static BookEntry updateBook(BookEntryUpdateRequest request,BookEntry entry){
        if(request.getStatus()!=null){
            entry.setStatus(ReadingStatus.valueOf(request.getStatus()));
        }
        if(request.getEndedOn()!=null){
            entry.setEndedOn(request.getEndedOn());
        }
        return entry;
    }

    public static BookEntryResponse toResponse(BookEntry entry){
        BookEntryResponse response = new BookEntryResponse();
        response.setId(entry.getId());
        response.setUsername(entry.getUser().getUsername());
        response.setBookTitle(entry.getBook().getTitle());
        response.setStatus(entry.getStatus().toString());
        response.setStartedOn(entry.getStartedOn());
        response.setEndedOn(entry.getEndedOn());
        return response;
    }


}
