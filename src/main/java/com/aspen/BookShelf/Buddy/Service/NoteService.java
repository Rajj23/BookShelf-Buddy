package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Dto.Note.NoteCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteResponse;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.Note;
import com.aspen.BookShelf.Buddy.Exception.ResourceNotFoundException;
import com.aspen.BookShelf.Buddy.Mapper.NoteMapper;
import com.aspen.BookShelf.Buddy.Repo.BookEntryRepo;
import com.aspen.BookShelf.Buddy.Repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private BookEntryRepo bookEntryRepo;

    public NoteResponse createNote(NoteCreateRequest request) throws ResourceNotFoundException {
        BookEntry bookEntry = bookEntryRepo.findById(request.getBookEntryId())
                .orElseThrow(()->new ResourceNotFoundException("BookEntry not found"));
        Note note = NoteMapper.toEntity(request,bookEntry);
        return NoteMapper.toResponse(noteRepo.save(note));
    }

    public NoteResponse updateNote(NoteUpdateRequest request) throws ResourceNotFoundException {
        Note note = noteRepo.findById(request.getNoteId())
                .orElseThrow(()->new ResourceNotFoundException("Note not found"));
        Note updatedNote = NoteMapper.updateNote(request,note);
        return NoteMapper.toResponse(noteRepo.save(updatedNote));
    }

    public List<NoteResponse> getAllNotes(){
       return noteRepo.findAll().stream()
               .map(NoteMapper::toResponse).collect(Collectors.toList());
    }

    public List<NoteResponse> getAllNotesByBookEntry(UUID bookEntryId) throws ResourceNotFoundException {
        BookEntry bookEntry = bookEntryRepo.findById(bookEntryId)
                .orElseThrow(()->new ResourceNotFoundException("BookEntry not found"));
        return noteRepo.findNoteByBookEntry(bookEntry).stream()
                .map(NoteMapper::toResponse).collect(Collectors.toList());
    }

    public List<NoteResponse> getAllNotesByCreatedAt(Instant createdAt){
        return noteRepo.getNotesByCreatedAt(createdAt).stream()
                .map(NoteMapper::toResponse).collect(Collectors.toList());
    }

    public List<NoteResponse> getAllNotesByContent(String content){
        return noteRepo.getNotesByContent(content).stream()
                .map(NoteMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteNote(UUID id) throws ResourceNotFoundException {
        Note note = noteRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Can't find Note with this id to delete: "+id));
         noteRepo.delete(note);
    }

}
