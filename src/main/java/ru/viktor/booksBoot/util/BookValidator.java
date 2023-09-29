package ru.viktor.booksBoot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.viktor.booksBoot.models.Book;
import ru.viktor.booksBoot.services.BookService;


@Component
public class BookValidator implements Validator {
    private final BookService bookService;

    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
