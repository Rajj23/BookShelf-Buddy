package com.aspen.BookShelf.Buddy.Controller;


import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryResponse;
import com.aspen.BookShelf.Buddy.Dto.BookEntry.BookEntryUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.ReadingStatus;
import com.aspen.BookShelf.Buddy.Entity.User;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Service.BookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/BookEntry")
public class BookEntryController {

    @Autowired
    public BookEntryService bookEntryService;

    @PostMapping("/create")
    public ResponseEntity<BookEntryResponse> createBookEntries(@RequestBody BookEntryCreateRequest request) throws ResourceNotFoundException {
        BookEntryResponse entry = bookEntryService.createBookEntry(request);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BookEntryResponse> updateBookEntries(@RequestBody BookEntryUpdateRequest entry) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookEntryService.updateBookEntry(entry),HttpStatus.OK);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<BookEntryResponse> getBookEntriesById(@PathVariable UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookEntryService.getBookEntriesById(id),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookEntryResponse>> getAllBookEntries() {
        return new ResponseEntity<>(bookEntryService.getAllBookEntries(), HttpStatus.OK);
    }

    @GetMapping("/get/status/{status}")
    public ResponseEntity<BookEntryResponse> getBookEntriesByStatus(@PathVariable ReadingStatus status) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookEntryService.getBookEntriesByStatus(status),HttpStatus.OK);
    }

    @GetMapping("/get/wishlist/{userId}")
    public ResponseEntity<List<BookEntryResponse>> getWishlist(@PathVariable UUID userId) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookEntryService.getWishlistByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/get/user/{user}")
    public ResponseEntity<BookEntryResponse> getBookEntriesByUser(@PathVariable User user){
        return new ResponseEntity<>(bookEntryService.getBookEntriesByUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookEntries(@PathVariable UUID id) throws ResourceNotFoundException {
       bookEntryService.deleteBookEntries(id);
       return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }


}


