package ru.viktor.booksBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktor.booksBoot.models.Book;
import ru.viktor.booksBoot.models.Person;
import ru.viktor.booksBoot.repositories.BookRepository;
import ru.viktor.booksBoot.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(int id){
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId_book(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void addReader(Integer id_book, Person person, Book book){
        Book editedBook = bookRepository.findById(id_book).get();
        editedBook.setOwner(person);
    }

    @Transactional
    public void deleteReader(Integer id_book, Book book){
        Book editedBook = bookRepository.findById(id_book).get();
        editedBook.setOwner(null);
    }
}