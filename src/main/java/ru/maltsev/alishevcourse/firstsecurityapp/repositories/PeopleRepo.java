package ru.maltsev.alishevcourse.firstsecurityapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maltsev.alishevcourse.firstsecurityapp.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepo extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
