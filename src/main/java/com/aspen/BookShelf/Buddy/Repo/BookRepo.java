package com.aspen.BookShelf.Buddy.Repo;

import com.aspen.BookShelf.Buddy.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepo extends JpaRepository<Book, UUID> {
    Book findBookByTitle(String title);

    Book findBookByAuthor(String author);

    Book findBookByGenre(String genre);
}
