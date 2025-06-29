package com.aspen.BookShelf.Buddy.Mapper;

import com.aspen.BookShelf.Buddy.Dto.Note.NoteCreateRequest;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteResponse;
import com.aspen.BookShelf.Buddy.Dto.Note.NoteUpdateRequest;
import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.Note;

import java.time.Instant;

public class NoteMapper {

    public static Note toEntity(NoteCreateRequest request, BookEntry bookEntry){
        Note note = new Note();
        note.setContent(request.getContent());
        note.setBookEntry(bookEntry);
        note.setCreatedAt(Instant.now());
        return note;
    }

    public static Note updateNote(NoteUpdateRequest request,Note note){
        if(request.getContent()!=null&&!request.getContent().trim().isEmpty()){
            note.setContent(request.getContent());
        }
        return note;
    }

    public static NoteResponse toResponse(Note note){
        NoteResponse response = new NoteResponse();
        response.setId(note.getId());
        response.setContent(note.getContent());
        response.setCreatedAt(note.getCreatedAt());
        response.setBookEntryId(note.getBookEntry().getId());
        return response;
    }

}
