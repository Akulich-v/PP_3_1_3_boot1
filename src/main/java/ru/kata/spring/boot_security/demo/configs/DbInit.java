package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;

@Component
public class DbInit {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private User tempAdmin;
    private User tempUser;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    public DbInit(UsersRepository usersRepository
            , RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void postConstruct() {

        Role adminRole = roleRepository.findById(2L).orElse(null);
        Role userRole = roleRepository.findById(1L).orElse(null);


        tempAdmin = new User("tempAdmin"
                , webSecurityConfig.getPasswordEncoder().encode("admin_password")
                , "FirstNameTempAdmin"
                , "LastNameTempAdmin"
                , "E-mailTempAdmin@email.ru");
        tempAdmin.setRoles(Collections.singleton(adminRole));

        tempUser = new User("tempUser"
                , webSecurityConfig.getPasswordEncoder().encode("user_password")
                , "FirstNameTempUser"
                , "LastNameTempUser"
                , "E-mailTempUser@email.ru");
        tempUser.setRoles(Collections.singleton(userRole));


        usersRepository.save(tempAdmin);
        usersRepository.save(tempUser);
    }

    @PreDestroy
    private void preDestroy() {
        if (usersRepository.existsById(tempAdmin.getId())) {
            usersRepository.delete(tempAdmin);
        }
        if (usersRepository.existsById(tempUser.getId())) {
            usersRepository.delete(tempUser);
        }
    }

}
