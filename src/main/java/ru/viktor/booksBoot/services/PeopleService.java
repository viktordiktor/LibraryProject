package ru.viktor.booksBoot.services;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktor.booksBoot.models.Book;
import ru.viktor.booksBoot.models.Person;
import ru.viktor.booksBoot.repositories.PeopleRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Optional<Person> findOne(int id){
        return peopleRepository.findById(id);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    public List<Book> allBooks(Integer id){
        // return jdbcTemplate.query("SELECT * FROM book WHERE id_person = ?", new Object[]{id},
        //       new BeanPropertyRowMapper<>(Book.class));
        Person person = peopleRepository.findById(id).get();
        return person.getBooks();
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId_person(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
