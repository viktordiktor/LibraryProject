package ru.viktor.booksBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktor.booksBoot.models.Person;
import ru.viktor.booksBoot.services.PeopleService;
import ru.viktor.booksBoot.util.PersonValidator;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    //private final BookValidator bookValidator;
    private final PeopleService peopleService;

    public PeopleController(PersonValidator personValidator,
                            PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "/people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "/people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) return "/people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable Integer id){
        if(peopleService.findOne(id).isPresent()){
            model.addAttribute("person" ,peopleService.findOne(id).get());
            model.addAttribute("books", peopleService.allBooks(id));
            return "/people/show";
        }
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
