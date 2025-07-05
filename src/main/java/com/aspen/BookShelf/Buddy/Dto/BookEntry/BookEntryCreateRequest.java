package com.aspen.BookShelf.Buddy.Dto.BookEntry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntryCreateRequest {

    private UUID userId;
    private UUID bookId;
    private String status;
    private Instant startedOn;

}
