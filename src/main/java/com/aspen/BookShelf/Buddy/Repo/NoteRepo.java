package com.aspen.BookShelf.Buddy.Repo;

import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface NoteRepo extends JpaRepository<Note, UUID> {
    List<Note> findNoteByBookEntry(BookEntry bookEntry);

    List<Note> getNotesByCreatedAt(Instant createdAt);

    List<Note> getNotesByContent(String content);
}
