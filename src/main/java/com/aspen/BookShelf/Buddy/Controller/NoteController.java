package com.aspen.BookShelf.Buddy.Controller;


import com.aspen.BookShelf.Buddy.Dto.Note.NoteCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteResponse;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteUpdateRequest;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/note")
public class NoteController{

    @Autowired
    public NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteCreateRequest request) throws ResourceNotFoundException {
            NoteResponse create = noteService.createNote(request);
            return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<NoteResponse> updateNote(@RequestBody NoteUpdateRequest request) throws ResourceNotFoundException {
        NoteResponse update = noteService.updateNote(request);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<NoteResponse>> getAllNotes(){
        return new ResponseEntity<>(noteService.getAllNotes(),HttpStatus.OK);
    }

    @GetMapping("/get/bookentry/id/{id}")
    public ResponseEntity<List<NoteResponse>> getAllNotesByBookEntry(@PathVariable UUID bookEntryId) throws ResourceNotFoundException {
        return new ResponseEntity<>(noteService.getAllNotesByBookEntry(bookEntryId),HttpStatus.OK);
    }

    @GetMapping("/get/time")
    public ResponseEntity<List<NoteResponse>> getAllNoteByCreatedAt(@RequestBody Instant created){
        return new ResponseEntity<>(noteService.getAllNotesByCreatedAt(created),HttpStatus.OK);
    }

    @GetMapping("/get/content")
    public ResponseEntity<List<NoteResponse>> getAllNotesByContent(@RequestBody String content){
        return new ResponseEntity<>(noteService.getAllNotesByContent(content),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteNote(@PathVariable UUID id) throws ResourceNotFoundException {
        noteService.deleteNote(id);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

}
