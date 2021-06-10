package it.univaq.mwt.myhealth.configuration;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("it.univaq.mwt.myhealth")
@EnableTransactionManagement
public class WebAppContext {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private Properties jpaProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
 
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(new String[]{"it.univaq.mwt.myhealth.domain"});
        entityManagerFactory.setPersistenceXmlLocation("classpath:persistence.xml");
        entityManagerFactory.setPersistenceUnitName("persistenceUnit");
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setJpaDialect(jpaDialect());

        return entityManagerFactory;
    }
    
    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    @Autowired
    public PlatformTransactionManager 
        transactionManager(EntityManagerFactory emFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emFactory);

        return txManager;
    }
}
