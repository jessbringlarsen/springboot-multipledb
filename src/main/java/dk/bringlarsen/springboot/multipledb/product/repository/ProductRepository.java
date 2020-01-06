package dk.bringlarsen.springboot.multipledb.product.repository;

import dk.bringlarsen.springboot.multipledb.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@Transactional(value = "productTransactionManager")
public class ProductRepository {

    //@PersistenceContext(unitName = "product") - Inject the EM by using the JPAContext to avoid relying on unitName
    EntityManager entityManager;

    @Autowired
    public ProductRepository(JpaContext context) {
        this.entityManager = context.getEntityManagerByManagedType(Product.class);
    }

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
