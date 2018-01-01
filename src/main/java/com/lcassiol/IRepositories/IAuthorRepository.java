package com.lcassiol.IRepositories;

import com.lcassiol.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
}
