package com.lcassiol.services;

import com.lcassiol.IRepositories.IAuthorRepository;
import com.lcassiol.IServices.IAuthorService;
import com.lcassiol.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
