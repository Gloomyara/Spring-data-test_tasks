package ru.antonovmikhail.jdbc.book.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoIn;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoOut;
import ru.antonovmikhail.jdbc.book.model.dto.NewBookDto;
import ru.antonovmikhail.jdbc.book.service.BookService;

import java.util.List;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public ResponseEntity<List<BookDtoOut>> getAll() {
        log.info("Received GET api/v1/books request.");
        return ResponseEntity.ok(bookService.findAll());
    }


    @GetMapping("books/{id}")
    public ResponseEntity<BookDtoOut> getBook(@Positive Long id) {
        log.info("Received GET api/v1/books/{} request.", id);
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("book")
    public ResponseEntity<BookDtoOut> saveNewBook(
            @Valid @RequestBody NewBookDto dtoIn) {
        log.info("Received POST api/v1/books request, orderDtoIn: {}.", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveNewBook(dtoIn));
    }

    @PutMapping("book")
    public ResponseEntity<BookDtoOut> put(@Valid @RequestBody BookDtoIn dtoIn) {
        log.info("Received PUT api/v1/books request, orderDtoIn = {}", dtoIn);
        return ResponseEntity.ok(bookService.update(dtoIn));
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<BookDtoOut> delete(@Positive Long id) {
        log.info("Received DELETE api/v1/books/{} request.", id);
        return ResponseEntity.ok(bookService.delete(id));
    }
}



