package dk.bringlarsen.springboot.multipledb.user.repository;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(value = "transactionManager")
public class UserRepository {

    //@PersistenceContext(unitName = "users") - Inject the EM by using the JPAContext to avoid relying on unitName
    EntityManager entityManager;

    @Autowired
    public UserRepository(JpaContext context) {
        this.entityManager = context.getEntityManagerByManagedType(User.class);
    }

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
