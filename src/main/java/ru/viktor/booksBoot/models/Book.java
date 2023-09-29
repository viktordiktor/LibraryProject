package ru.viktor.booksBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer id_book;
    @NotEmpty
    @Size(min = 2, max = 120, message = "Name should be between 2 and 120 chars")
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 120, message = "Author should be between 2 and 120 chars")
    @Column(name = "author")
    private String author;
    @Min(value=1700, message = "Age in diapason between 1700 and 2023") @Max(value=2023, message = "Age in diapason between 1700 and 2023")
    @Column(name = "year_book")
    private int year_book;
    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person owner;

    public Book(){}

    public Book(Integer id_book, String name, String author, int year_book) {
        this.id_book = id_book;
        this.name = name;
        this.author = author;
        this.year_book = year_book;
    }

    public Book(String name, String author, int year_book, Person owner) {
        this.name = name;
        this.author = author;
        this.year_book = year_book;
        this.owner = owner;
    }

    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_book() {
        return year_book;
    }

    public void setYear_book(int year_book) {
        this.year_book = year_book;
    }


    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}