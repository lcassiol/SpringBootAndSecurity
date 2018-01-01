package com.lcassiol.IRepositories;

import com.lcassiol.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);
}
