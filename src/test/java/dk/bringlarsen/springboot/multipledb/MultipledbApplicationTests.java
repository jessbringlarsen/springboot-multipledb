package dk.bringlarsen.springboot.multipledb;

import dk.bringlarsen.springboot.multipledb.product.model.Product;
import dk.bringlarsen.springboot.multipledb.product.repository.ProductRepository;
import dk.bringlarsen.springboot.multipledb.user.model.User;
import dk.bringlarsen.springboot.multipledb.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class MultipledbApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;


	@Test
	void testPersistUser() {
		User user = new User();
		user.setAge(21);
		user.setName("test");
		user.setEmail("test@test.dk");

		userRepository.save(user);
	}

	@Test
	void testPersistProduct() {
		Product product = new Product();
		product.setName("test");
		product.setPrice(2);

		productRepository.save(product);
	}

}
