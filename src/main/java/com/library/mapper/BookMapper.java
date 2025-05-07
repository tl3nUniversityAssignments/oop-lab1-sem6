package com.library.mapper;

import com.library.dto.BookDTO;
import com.library.model.Author;
import com.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "book.title", target = "title")
    @Mapping(source = "book.isbn", target = "isbn")
    @Mapping(source = "book.publicationYear", target = "publicationYear")
    @Mapping(source = "authorNames", target = "authorNames")
    @Mapping(source = "availableCopies", target = "availableCopies")
    BookDTO toDTO(Book book, List<String> authorNames, int availableCopies);
}
