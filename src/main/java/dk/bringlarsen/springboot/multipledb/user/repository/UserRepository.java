package dk.bringlarsen.springboot.multipledb.user.repository;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(value = "transactionManager")
public class UserRepository {

    @PersistenceContext(unitName = "users")
    EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public List<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
