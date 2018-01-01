package com.lcassiol.services;

import com.lcassiol.IRepositories.IBookRepository;
import com.lcassiol.IServices.IBookService;
import com.lcassiol.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookRepository iBookRepository;

    @Override
    public List<Book> getAll() {
        return iBookRepository.findAll();
    }
}
