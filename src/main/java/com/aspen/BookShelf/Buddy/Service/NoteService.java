package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.Note;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    public Note createNote(Note note){
        return noteRepo.save(note);
    }

    public Note updateNote(UUID id, Note note) throws ResourceNotFoundException {
        Note user = noteRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find Note with this id to update: "+id));
        user.setContent(note.getContent());
        user.setBookEntry(note.getBookEntry());
        user.setCreatedAt(note.getCreatedAt());
        return noteRepo.save(user);
    }

    public List<Note> getAllNotes(){
       return noteRepo.findAll();
    }

    public List<Note> getAllNotesByBookEntry(BookEntry bookEntry){
        return noteRepo.findNoteByBookEntry(bookEntry);
    }

    public List<Note> getAllNotesByCreatedAt(Instant createdAt){
        return noteRepo.getNotesByCreatedAt(createdAt);
    }

    public List<Note> getAllNotesByContent(String content){
        return noteRepo.getNotesByContent(content);
    }

    public void deleteNote(UUID id) throws ResourceNotFoundException {
        Note note = noteRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Can't find Note with this id to delete: "+id));
         noteRepo.delete(note);
    }

}
