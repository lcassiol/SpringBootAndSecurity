package com.lcassiol.controller;

import com.lcassiol.IRepositories.IRoleRepository;
import com.lcassiol.IRepositories.IUserRepository;
import com.lcassiol.IServices.IAuthorService;
import com.lcassiol.IServices.IBookService;
import com.lcassiol.IServices.IUserService;
import com.lcassiol.entities.Role;
import com.lcassiol.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
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
    private IRoleRepository roleRepository;

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


    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public String users(Model model) {

        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value="/user-registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit-users")
    public String editUsers(Model model) {

        model.addAttribute("user", userService.findUserByEmail("cassio@xarx.co"));
        model.addAttribute("roles", roleRepository.findAll());
        return "edit-users";
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public ModelAndView addRole(Role role) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("successMessage", "User has been registered successfully, click on top buttom for redirect to login page");
        //modelAndView.addObject("user", userService.updateUser(user));
        modelAndView.setViewName("edit-users");

        return modelAndView;
    }

    @RequestMapping(value = "/edit-users", method = RequestMethod.POST)
    public ModelAndView editUsers(User user) {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject("successMessage", "User has been registered successfully, click on top buttom for redirect to login page");
        modelAndView.addObject("user", userService.updateUser(user));
        modelAndView.setViewName("edit-users");

        return modelAndView;
    }

    @RequestMapping(value = "/user-registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user-registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully, click on top buttom for redirect to login page");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("user-registration");

        }
        return modelAndView;
    }
}
