package ru.antonovmikhail.jdbc.book.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.antonovmikhail.jdbc.book.model.Book;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoIn;
import ru.antonovmikhail.jdbc.book.model.dto.BookDtoOut;
import ru.antonovmikhail.jdbc.book.model.dto.NewBookDto;

import java.util.List;

@Mapper
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book toEntity(NewBookDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookDtoOut toDto(Book entity);

    List<BookDtoOut> toDto(List<Book> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(BookDtoIn dto, @MappingTarget Book entity);
}
