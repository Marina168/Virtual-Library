package com.example.library.Controllers;

import com.example.library.Entities.BookEntity;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class BookControllerAssembler implements RepresentationModelAssembler<BookEntity, EntityModel<BookEntity>> {

    @SneakyThrows
    @Override
    public EntityModel<BookEntity> toModel(BookEntity book) {
            return EntityModel.of(book, WebMvcLinkBuilder.linkTo(methodOn(BookController.class).findBook(book.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

    }
}

