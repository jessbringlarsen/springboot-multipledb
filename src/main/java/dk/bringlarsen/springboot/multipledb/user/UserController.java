package dk.bringlarsen.springboot.multipledb.user;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class UserController {

    @PersistenceContext(unitName = "users")
    private EntityManager entityManager;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(entityManager.createQuery("select u from Users u").getResultList());
    }
}
