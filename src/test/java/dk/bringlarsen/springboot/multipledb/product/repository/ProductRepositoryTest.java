package dk.bringlarsen.springboot.multipledb.product.repository;

import dk.bringlarsen.springboot.multipledb.product.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void testIdIsAssigned() {
        Product product = buildAndSaveProduct();

        assertTrue(product.getId() > 0);
    }

    @Test
    void testFindById() {
        Product product = buildAndSaveProduct();

        Product reget = repository.findById(product.getId()).get();

        assertEquals(product, reget);
    }

    private Product buildAndSaveProduct() {
        Product product = new Product()
                .setName("rice")
                .setPrice(10);

        return repository.save(product);
    }
}