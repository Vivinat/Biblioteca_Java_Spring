package vinicius.biblioteca.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record BorrowRequestDTO(UUID clientId,
                               UUID bookId,
                               LocalDate date_borrowed) {}
