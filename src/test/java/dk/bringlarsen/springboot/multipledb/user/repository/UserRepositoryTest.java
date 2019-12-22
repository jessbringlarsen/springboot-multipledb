package dk.bringlarsen.springboot.multipledb.user.repository;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testIdIsAssigned() {
        User user = buildAndSaveUSer();

        assertTrue(user.getId() > 0);
    }

    @Test
    void testFindById() {
        User user = buildAndSaveUSer();

        User fetchedUser = userRepository.findById(user.getId()).get();

        assertEquals(user, fetchedUser);
    }

    private User buildAndSaveUSer() {
        User user = new User()
                .setAge(21)
                .setName("test")
                .setEmail("test@test.dk");

        return userRepository.save(user);
    }
}