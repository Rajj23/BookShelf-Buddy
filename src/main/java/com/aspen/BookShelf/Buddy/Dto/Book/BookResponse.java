package com.aspen.BookShelf.Buddy.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private UUID bookId;
    private String title;
    private String author;
    private String genre;
    private int pages;
    private LocalDate publishedDate;
    private String description;
}
