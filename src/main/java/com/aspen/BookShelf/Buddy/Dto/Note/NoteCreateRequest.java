package com.aspen.BookShelf.Buddy.Dto.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteCreateRequest {
    private String content;
    private UUID bookEntryId;
}
