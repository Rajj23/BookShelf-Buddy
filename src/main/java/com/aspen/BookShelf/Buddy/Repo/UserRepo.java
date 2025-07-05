package com.aspen.BookShelf.Buddy.Repo;

import com.aspen.BookShelf.Buddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String username);
}
