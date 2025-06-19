package com.aspen.BookShelf.Buddy.Dto.BookEntry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntryResponse {
    private UUID id;
    private String bookTitle;
    private String username;
    private String status;
    private Instant startedOn;
    private Instant endedOn;
}
