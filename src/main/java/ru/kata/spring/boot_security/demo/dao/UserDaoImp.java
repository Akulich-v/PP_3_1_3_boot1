package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
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

        updatedUser.setId(updatedUser.getId());
        updatedUser.setUsername(updatedUser.getUsername());
        updatedUser.setPassword(updatedUser.getPassword());
        updatedUser.setFirstName(updatedUser.getFirstName());
        updatedUser.setLastName(updatedUser.getLastName());
        updatedUser.setEmail(updatedUser.getEmail());
        updatedUser.setRoles(updatedUser.getRoles());
        entityManager.merge(updatedUser);
    }
}
