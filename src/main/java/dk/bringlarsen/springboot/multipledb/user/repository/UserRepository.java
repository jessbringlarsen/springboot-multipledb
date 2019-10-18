package dk.bringlarsen.springboot.multipledb.user.repository;

import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> { }


