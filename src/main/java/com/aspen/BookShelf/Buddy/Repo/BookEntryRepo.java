package com.aspen.BookShelf.Buddy.Repo;

import com.aspen.BookShelf.Buddy.Entity.BookEntry;
import com.aspen.BookShelf.Buddy.Entity.ReadingStatus;
import com.aspen.BookShelf.Buddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookEntryRepo extends JpaRepository<BookEntry, UUID> {
    BookEntry findBookEntriesByStatus(ReadingStatus status);

    BookEntry findBookEntriesByUser(User user);
}
