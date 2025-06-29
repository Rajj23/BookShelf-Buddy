package com.aspen.BookShelf.Buddy.Dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {

    private UUID bookId;
    private String title;
    private String author;
    private  String genre;
    private Integer pages;
    private LocalDate publishedDate;
    private String description;

}
