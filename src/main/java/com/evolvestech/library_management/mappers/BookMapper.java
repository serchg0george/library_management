package com.evolvestech.library_management.mappers;

import com.evolvestech.library_management.dtos.BookDto;
import com.evolvestech.library_management.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper extends BaseMapper<Book, BookDto> {

    @Override
    BookDto mapEntityToDto(Book entity);

    @Override
    Book mapDtoToEntity(BookDto dto);
}