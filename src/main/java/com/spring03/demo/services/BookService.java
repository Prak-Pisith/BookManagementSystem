package com.spring03.demo.services;

import com.spring03.demo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book findOneBook(Integer id);

    Boolean update(Book book);

    Boolean dalete(Integer id);

    Boolean create(Book book);
}
