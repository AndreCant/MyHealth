package it.univaq.mwt.myhealth.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("dev")
@Configuration
public class DevDataConfig{
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myhealth?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("");
 
        return dataSource;
    }
	
    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
       // properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.ogm.datastore.create_database", true);
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }
}
