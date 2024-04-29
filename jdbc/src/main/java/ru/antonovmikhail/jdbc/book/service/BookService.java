package ru.antonovmikhail.jdbc.book.service;

import ru.antonovmikhail.jdbc.book.model.dto.BookDtoIn;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoOut;
import ru.antonovmikhail.jdbc.book.model.dto.NewBookDto;

import java.util.List;

public interface BookService {


    BookDtoOut getBookById(Long id);

    BookDtoOut saveNewBook(NewBookDto dto);

    BookDtoOut update(BookDtoIn dtoIn);

    List<BookDtoOut> findAll();

    BookDtoOut delete(Long id);
}
