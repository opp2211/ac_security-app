package ru.maltsev.alishevcourse.firstsecurityapp.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maltsev.alishevcourse.firstsecurityapp.models.Person;
import ru.maltsev.alishevcourse.firstsecurityapp.services.PeopleService;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PeopleService peopleService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.existByUsername(person.getUsername()))
            errors.rejectValue("username", "", "Username already exists");
    }
}