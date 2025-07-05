package com.aspen.BookShelf.Buddy.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
    private String title;
    private String author;
    private String genre;
    private int pages;
    private LocalDate publishedDate;
    private String description;
}
