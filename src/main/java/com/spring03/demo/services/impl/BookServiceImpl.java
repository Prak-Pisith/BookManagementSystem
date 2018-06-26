package com.spring03.demo.services.impl;

import com.spring03.demo.model.Book;
import com.spring03.demo.repositories.BookRepository;
import com.spring03.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Book> getAllBook() {

        return this.repository.getAll();
    }

    @Override
    public Book findOneBook(Integer id) {
        return this.repository.findOneBook(id);
    }

    @Override
    public Boolean update(Book book) {
        return this.repository.update(book);
    }

    @Override
    public Boolean dalete(Integer id) {
        return this.repository.delete(id);
    }

    @Override
    public Boolean create(Book book) {
        return this.repository.create(book);
    }
}
