package vinicius.biblioteca.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrow_id")
    private Integer borrow_id;

    @ManyToOne
    @JsonProperty("clientId")
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;

    @ManyToOne
    @JsonProperty("bookId")
    @JoinColumn(name = "book_id", nullable = false)
    private Book bookId;

    @Column(name = "date_borrowed", nullable = false)
    private LocalDate date_borrowed;

    @Column(name = "date_returned")
    private LocalDate date_returned;

    @JsonProperty("returned")
    @Column(name = "returned")
    private boolean returned;

}
