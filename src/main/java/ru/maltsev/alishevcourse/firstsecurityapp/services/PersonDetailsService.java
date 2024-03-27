package ru.maltsev.alishevcourse.firstsecurityapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maltsev.alishevcourse.firstsecurityapp.repositories.PeopleRepo;
import ru.maltsev.alishevcourse.firstsecurityapp.security.PersonDetails;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepo peopleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PersonDetails(
                peopleRepo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }
}
