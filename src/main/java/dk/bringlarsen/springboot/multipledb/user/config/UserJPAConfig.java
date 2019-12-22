package dk.bringlarsen.springboot.multipledb.user.config;

import com.zaxxer.hikari.HikariDataSource;
import dk.bringlarsen.springboot.multipledb.user.model.User;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UserJPAConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.user")
    public DataSourceProperties userDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @FlywayDataSource
    @ConfigurationProperties(prefix = "spring.datasource.user.configuration")
    public HikariDataSource userDataSource() {
        return userDatasourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userDataSource())
                .packages(User.class)
                .persistenceUnit("users")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
        return transactionManager;
    }

    /*@Primary
    @Bean(name = "userEntityManager")
    @PersistenceContext(unitName = "user")
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[]{"dk.bringlarsen.springboot.multipledb.user.model"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
      //  properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(properties);
        em.setPersistenceUnitName("user");

        return em;
    }*/
}
