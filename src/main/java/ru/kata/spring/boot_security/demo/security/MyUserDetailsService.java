package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    /**
     * Оставил "My", чтоб были разные названия и не делать длинный импорт при имплементации
     */

    private final UsersRepository usersRepository;

    @Autowired
    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByUsername(String usersname) {
        return usersRepository.findByUsername(usersname);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        user.getRoles().size();
        return new UserPrincipal(user);
        }
    }
