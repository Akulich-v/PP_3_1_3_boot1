package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }
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
    public void update(long id, User updatedUser) {
        User userToBeUpdated = findOne(id);
        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }
}
