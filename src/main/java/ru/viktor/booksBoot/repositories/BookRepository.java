package ru.viktor.booksBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktor.booksBoot.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
