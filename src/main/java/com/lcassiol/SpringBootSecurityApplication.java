package com.lcassiol;

import com.lcassiol.IRepositories.*;
import com.lcassiol.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SpringBootSecurityApplication {

	@Autowired
	IBookRepository bookRepository;

	@Autowired
	IAuthorRepository authorRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

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

			Role rol = new Role();
			rol.setRole("ROLE_BOOKS");
			rol = roleRepository.save(rol);

			Role rol2 = new Role();
			rol2.setRole("PG_REL_EQUIPE");
			rol2 = roleRepository.save(rol2);

			Role rol3 = new Role();
			rol3.setRole("ROLE_AUTHORS");
			rol3 = roleRepository.save(rol3);

			Role rol4 = new Role();
			rol4.setRole("ROLE_ADMIN");
			rol4 = roleRepository.save(rol4);

			List<Role> rolesOnDb = roleRepository.findAll();
			System.out.println("------------------=-=-=-=-");
			rolesOnDb.forEach(System.out::println);

			Set<Role> roles = new HashSet<>();
			roles.add(rol);
			roles.add(rol2);
			//roles.add(rol3);
			roles.add(rol4);

			User user = new User();
			user.setId(1);
			user.setActive(true);
			user.setEmail("cassio@xarx.co");
			user.setName("Cassio");
			user.setLastName("carvalhis");
			user.setPassword("$2a$10$HDabdSxfveSXzB.O6KI9X.yqn6D2Wv/F8P0xyPPlz6LMd0qIitJ1a");
			user.setRoles(roles);

			userRepository.save(user);
		};
	}

}
