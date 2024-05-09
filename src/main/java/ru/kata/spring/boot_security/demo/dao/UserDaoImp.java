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
        //user.setPassword(webSecurityConfig.getPasswordEncoder().encode(user.getPassword()));
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
    public void update(long id, User updatedUser) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }
}
