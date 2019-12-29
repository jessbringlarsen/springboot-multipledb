package dk.bringlarsen.springboot.multipledb.user;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import dk.bringlarsen.springboot.multipledb.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private UserRepository userRepository;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getById(@PathVariable final Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody UserDTO userDTO) throws URISyntaxException {
        User user = new User()
                .setName(userDTO.name)
                .setEmail(userDTO.email)
                .setAge(userDTO.age);
        user = userRepository.save(user);
        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    public static class UserDTO {
        public String name, email;
        public int age;
    }
}
