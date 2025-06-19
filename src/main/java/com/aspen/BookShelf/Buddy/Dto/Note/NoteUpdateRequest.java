package com.aspen.BookShelf.Buddy.Dto.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteUpdateRequest {
    private UUID noteId;
    private String content;
}
