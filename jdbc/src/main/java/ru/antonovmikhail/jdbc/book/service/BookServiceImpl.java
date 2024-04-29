package ru.antonovmikhail.jdbc.book.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.jdbc.book.mapper.BookMapper;
import ru.antonovmikhail.jdbc.book.model.Book;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoIn;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoOut;
import ru.antonovmikhail.jdbc.book.model.dto.NewBookDto;
import ru.antonovmikhail.jdbc.book.repository.BookRepository;
import ru.antonovmikhail.jdbc.user.model.User;
import ru.antonovmikhail.jdbc.user.repository.UserRepository;
import ru.antonovmikhail.jdbc.util.handler.EntityNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final UserRepository userRepository;
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Transactional(readOnly = true)
    @Override
    public BookDtoOut getBookById(Long id) throws EntityNotFoundException {
        Book book = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        User user = userRepository.findById(book.getAuthor_id()).orElseThrow(() -> new EntityNotFoundException());
        BookDtoOut dto = mapper.toDto(book);
        dto.setAuthorName(user.getName());
        return dto;
    }

    @Override
    public BookDtoOut saveNewBook(NewBookDto dtoIn) throws EntityNotFoundException {
        User user = userRepository.findById(dtoIn.getAuthorId()).orElseThrow(() -> new EntityNotFoundException());
        Book book = mapper.toEntity(dtoIn);
        BookDtoOut dto = mapper.toDto(repository.save(book));
        dto.setAuthorName(user.getName());
        return dto;
    }


    @Override
    public BookDtoOut update(BookDtoIn dtoIn) throws EntityNotFoundException {
        User user = userRepository.findById(dtoIn.getAuthorId()).orElseThrow(() -> new EntityNotFoundException());
        Book book = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        mapper.update(dtoIn, book);
        BookDtoOut dto = mapper.toDto(repository.update(book));
        dto.setAuthorName(user.getName());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public BookDtoOut delete(Long id) throws EntityNotFoundException {
        Book book = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return mapper.toDto(book);
    }
}
