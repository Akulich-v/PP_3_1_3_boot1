package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;


    @Autowired
    public UserDaoImp(EntityManager entityManager, RoleRepository roleRepository) {
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User findOne(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    @Override
    public void update(User updatedUser) {
        Set<Role> roles = new HashSet<>();
        for (Role role : updatedUser.getRoles()) {
            Role persistedRole = roleRepository.findByName(role.getName());
            if (persistedRole != null) {
                roles.add(persistedRole);
            }
        }
        updatedUser.setRoles(roles);
        entityManager.merge(updatedUser);
    }

}

