package com.example.annualproject.userApi.service;

import org.springframework.stereotype.Controller;


//@CrossOrigin
@Controller
public class LoginController {
/*
    private final UserServices userServices;
    private final UserValidator userValidator;

    @Autowired
    public LoginController(UserServices userServices, UserValidator userValidator) {
        this.userServices = userServices;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        return "homePage";
    }

    @GetMapping(value = "/welcome")
    public String welcome(Model model) {
        return "welcomePage";
    }

    @GetMapping(value = "/login", produces = "text/html")
    public String getLoginPage(Model model) {
        //model.addAttribute("hello", "coucou");
        return "loginPage";
    }

    @GetMapping(value = "/error")
    public String error(Model model) {
        return "errorPage";
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(Model model, @ModelAttribute("pseudo") String pseudo,
                        @ModelAttribute("password") String password) {
        if (userServices.verifyUser(pseudo, password) == true) {
            return "redirect:welcome";
        } else {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/registration", method = GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registrationPage";
    }

    @RequestMapping(value = "/registration", method = POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:registration";
        }
        model.addAttribute("pseudo", userForm.getPseudo());
        model.addAttribute("email", userForm.getEmail());
        model.addAttribute("password", userForm.getPassword());
        userServices.createUser(userForm.getPseudo(),
                userForm.getEmail(),
                userForm.getPassword(),
                Role.USER);
        return "redirect:welcome";
    }*/
}