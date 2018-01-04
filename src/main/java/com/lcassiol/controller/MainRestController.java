package com.lcassiol.controller;

import com.lcassiol.IRepositories.IUserRepository;
import com.lcassiol.IServices.IAuthorService;
import com.lcassiol.IServices.IBookService;
import com.lcassiol.IServices.IUserService;
import com.lcassiol.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainRestController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IUserService userService;

    @Autowired
    IUserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
    public String entrar(Model model) {
        return "entrar";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
    public String inicio() {
        return "inicio";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/books")
    public String books(Model model){

        model.addAttribute("books", bookService.getAll());

        System.out.println("Users here");
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

        return "books";
    }

	@RequestMapping(method = RequestMethod.GET, path = "/authors")
    public String authors(Model model) {

        model.addAttribute("authors", authorService.getAll());

	    return "authors";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/relatorio-equipe")
    public String relatorioEquipe() {
        return "relatorio-equipe";
    }
}
