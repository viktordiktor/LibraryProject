package ru.viktor.booksBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer id_person;
    @NotEmpty
    @Size(min = 3, max = 100)
    @Column(name = "FIO")
    private String FIO;

    @Min(value=1900) @Max(value=2023)
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, orphanRemoval=true, fetch = FetchType.EAGER)
    public List<Book> books;

    public Person(){}

    public Person(Integer id_person, String FIO, int year) {
        this.id_person = id_person;
        this.FIO = FIO;
        this.year = year;
    }

    public Person(String FIO, int year, List<Book> books) {
        this.FIO = FIO;
        this.year = year;
        this.books = books;
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
