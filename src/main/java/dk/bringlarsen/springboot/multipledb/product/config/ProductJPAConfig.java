package dk.bringlarsen.springboot.multipledb.product.config;

import com.zaxxer.hikari.HikariDataSource;
import dk.bringlarsen.springboot.multipledb.product.model.Product;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProductJPAConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.product")
    public DataSourceProperties productDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.product.configuration")
    public HikariDataSource productDataSource() {
        return productDatasourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(productDataSource())
                .packages(Product.class)
                .persistenceUnit("product")
                .build();
    }

    @Bean
    public PlatformTransactionManager productTransactionManager(EntityManagerFactoryBuilder builder){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(productEntityManagerFactory(builder).getObject());
        transactionManager.setDataSource(productDataSource());
        return transactionManager;
    }
}
