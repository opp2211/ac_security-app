package ru.maltsev.alishevcourse.firstsecurityapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maltsev.alishevcourse.firstsecurityapp.models.Person;
import ru.maltsev.alishevcourse.firstsecurityapp.repositories.PeopleRepo;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepo peopleRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean existByUsername(String username) {
        return peopleRepo.existsByUsername(username);
    }

    @Transactional
    public void registerNew(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepo.save(person);
    }
}