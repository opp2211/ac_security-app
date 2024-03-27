package ru.maltsev.alishevcourse.firstsecurityapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maltsev.alishevcourse.firstsecurityapp.models.Person;
import ru.maltsev.alishevcourse.firstsecurityapp.services.PeopleService;
import ru.maltsev.alishevcourse.firstsecurityapp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("person") @Valid Person person,
                           BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/register";

        peopleService.registerNew(person);

        return "redirect:auth/login";
    }
}