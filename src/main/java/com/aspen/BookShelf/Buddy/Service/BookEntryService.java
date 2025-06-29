package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryResponse;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.Book;
import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.ReadingStatus;
import com.aspen.BookShelf.Buddy.Entity.User;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Mapper.BookEntryMapper;
import com.aspen.BookShelf.Buddy.Repo.BookEntryRepo;
import com.aspen.BookShelf.Buddy.Repo.BookRepo;
import com.aspen.BookShelf.Buddy.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookEntryService {
    @Autowired
    private BookEntryRepo bookEntryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;



    public BookEntryResponse createBookEntry(BookEntryCreateRequest request) throws ResourceNotFoundException {
        User user = userRepo.findById(request.getBookId())
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID: " + request.getUserId()));
        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found with ID: " + request.getBookId()));
        BookEntry entry = BookEntryMapper.toEntity(request,user,book);
        return BookEntryMapper.toResponse(bookEntryRepo.save(entry));
    }

    public BookEntryResponse updateBookEntry(BookEntryUpdateRequest entry) throws ResourceNotFoundException {
        BookEntry existingBook = bookEntryRepo.findById(entry.getBookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found with ID: " + entry.getBookId()));
        BookEntry updateEntry = BookEntryMapper.updateBook(entry,existingBook);
        return BookEntryMapper.toResponse(bookEntryRepo.save(updateEntry));
    }

    public BookEntryResponse getBookEntriesById(UUID id) throws ResourceNotFoundException{
        BookEntry entry = bookEntryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with ID: "+id));
        return BookEntryMapper.toResponse(entry);
    }

    public List<BookEntryResponse> getAllBookEntries(){
        return bookEntryRepo.findAll().stream()
                .map(BookEntryMapper::toResponse).collect(Collectors.toList());
    }

    public BookEntryResponse getBookEntriesByStatus(ReadingStatus status) throws ResourceNotFoundException {
        BookEntry entry = bookEntryRepo.findBookEntriesByStatus(status);
        if(entry==null){
            throw new ResourceNotFoundException("No Book Entry found with status: " + status);
        }
        return BookEntryMapper.toResponse(entry);
    }

    public List<BookEntryResponse> getWishlistByUser(UUID userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<BookEntry> wishListEntries = bookEntryRepo.findByUserAndStatus(user,ReadingStatus.WANT_TO_READ);
        return wishListEntries.stream()
                .map(BookEntryMapper::toResponse)
                .toList();
    }

    public BookEntryResponse getBookEntriesByUser(User user){
        BookEntry entry = bookEntryRepo.findBookEntriesByUser(user);
        return BookEntryMapper.toResponse(entry);
    }

    public void deleteBookEntries(UUID id) throws ResourceNotFoundException{
        BookEntry entry = bookEntryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Can't find Book Entry with this id to delete: "+id));
        bookEntryRepo.delete(entry);
    }

}
