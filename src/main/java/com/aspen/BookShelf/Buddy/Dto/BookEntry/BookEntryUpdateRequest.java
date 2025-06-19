package com.aspen.BookShelf.Buddy.Dto.BookEntry;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntryUpdateRequest {
     private UUID bookId;
     @NotBlank(message = "Status must not be empty")
     private String status = null;
     private Instant endedOn = null;

}
