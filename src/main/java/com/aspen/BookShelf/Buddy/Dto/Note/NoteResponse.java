package com.aspen.BookShelf.Buddy.Dto.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private UUID id;
    private String content;
    private Instant createdAt;
    private UUID bookEntryId;
}
