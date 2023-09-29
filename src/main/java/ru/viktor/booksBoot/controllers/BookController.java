package ru.viktor.booksBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktor.booksBoot.models.Book;
import ru.viktor.booksBoot.models.Person;
import ru.viktor.booksBoot.services.BookService;
import ru.viktor.booksBoot.services.PeopleService;
import ru.viktor.booksBoot.util.BookValidator;


@Controller
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    public BookController(BookService bookService, PeopleService peopleService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookService.findAll());
        return "/books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book, Model model){
        return "/books/new";
    }

    @PatchMapping("/{id}/addReader")
    public String addReader(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person, @PathVariable Integer id){
        bookService.addReader(id, person, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/deleteReader")
    public String deleteReader(@ModelAttribute("book") Book book, @PathVariable Integer id){
        bookService.deleteReader(id, book);
        return "redirect:/books";
    }


    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()) return "/books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") Integer id, @ModelAttribute("person") Person person){
        if(bookService.findOne(id).isPresent()) {
            model.addAttribute("book", bookService.findOne(id).get());
            if((bookService.findOne(id).get().getOwner() != null))
                model.addAttribute("person", peopleService.findOne(bookService.findOne(id).get().getOwner().getId_person()));
            model.addAttribute("people", peopleService.findAll());
            return "/books/show";
        }
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id).get());
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") Book book, BindingResult bindingResult,
                           @PathVariable("id") Integer id){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()) return "/books/edit";
        bookService.update(id, book);
        return "redirect:/books";
    }
}
