package vinicius.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID book_id;

    @JsonProperty("bookName")
    @Column(name = "book_name", nullable = false, unique = true)
    private String book_name;

    @JsonProperty("genre")
    @Enumerated(EnumType.STRING)
    @Column(name = "book_genres", nullable = false)
    private BookGenres genre;

    @JsonProperty("publicationDate")
    @Column(name = "book_pub", nullable = false)
    private LocalDate publicationDate;

    private boolean borrowed;


}
