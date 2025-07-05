package com.aspen.BookShelf.Buddy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
      @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    String content;
    Instant createdAt;
    @ManyToOne
    @JoinColumn(name = "book_entry_id")
    BookEntry bookEntry;
}
