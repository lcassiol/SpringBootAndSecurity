package com.lcassiol;

import com.lcassiol.entities.Author;
import com.lcassiol.entities.Book;
import com.lcassiol.IRepositories.IAuthorRepository;
import com.lcassiol.IRepositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootSecurityApplication {

	@Autowired
	IBookRepository bookRepository;

	@Autowired
	IAuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Author author1 = new Author();
			author1.setBirthDay(new Date());
			author1.setName("Cassius7");
			author1.setNationality("Brazilian");

			author1 =  authorRepository.save(author1);

			Author author2 = new Author();
			author2.setBirthDay(new Date());
			author2.setName("Michael Breus");
			author2.setNationality("EUA");

			author2 = authorRepository.save(author2);

			Book b1 = new Book();
			b1.setName("Use a Cabeça C#");
			b1.setYear(2009);
			b1.setAuthor(author1);

			Book b2 = new Book();
			b2.setName("História das Guerras");
			b2.setYear(2007);
			b2.setAuthor(author1);

			Book b3 = new Book();
			b3.setName("O poder do quando");
			b3.setYear(2016);
			b3.setAuthor(author2);

			Book b4 = new Book();
			b4.setName("O poder do hábito");
			b4.setYear(2016);
			b4.setAuthor(author2);

			List<Book> insertBooks = new ArrayList<>();
			insertBooks.add(b1);
			insertBooks.add(b2);
			insertBooks.add(b3);
			insertBooks.add(b4);

			this.bookRepository.save(insertBooks);

			List<Book> books = bookRepository.findAll();
			books.forEach(System.out::println);

		};
	}

}
