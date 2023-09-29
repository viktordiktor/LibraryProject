package ru.viktor.booksBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktor.booksBoot.models.Person;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
