package dk.bringlarsen.springboot.multipledb.product.repository;

import dk.bringlarsen.springboot.multipledb.product.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional(value = "productTransactionManager")
public class ProductRepository {

    @PersistenceContext(unitName = "product")
    EntityManager entityManager;

    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
