package com.lcassiol.controller;

import com.lcassiol.IRepositories.IBookRepository;
import com.lcassiol.IServices.IBookService;
import com.lcassiol.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainRestController {

    @Autowired
    private IBookService bookService;



	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
    public String entrar() {
        return "entrar";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
    public String inicio() {
        return "inicio";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/books")
    public String projetos(Model model){

        model.addAttribute("books", bookService.getAll());

        return "books";
    }

	@RequestMapping(method = RequestMethod.GET, path = "/authors")
    public String relatorioCustos() {
        return "authors";
    }
	
	@RequestMapping(method = RequestMethod.GET, path = "/relatorio-equipe")
    public String relatorioEquipe() {
        return "relatorio-equipe";
    }
}
