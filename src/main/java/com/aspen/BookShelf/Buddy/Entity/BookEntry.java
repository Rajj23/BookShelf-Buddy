package com.aspen.BookShelf.Buddy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private ReadingStatus status;

    private Instant startedOn;
    private Instant endedOn;
    @OneToMany(mappedBy = "bookEntry",cascade =CascadeType.ALL)
    private List<Note> notes;
}
