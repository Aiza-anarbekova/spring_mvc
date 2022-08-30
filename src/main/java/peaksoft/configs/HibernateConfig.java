package peaksoft.configs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class HibernateConfig {
    private final Environment environment;
    @Autowired
    public HibernateConfig(Environment env) {
        this.environment = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean managerFactoryBean(){
        LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();
        manager.setJpaVendorAdapter(jpaVendorAdapter());
        manager.setDataSource(dataSource());
        manager.setPackagesToScan("peaksoft.model");
        manager.setJpaProperties(properties());
        return manager;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(environment.getProperty("db.driver"));
        source.setUrl(environment.getProperty("db.url"));
        source.setUsername(environment.getProperty("db.userName"));
        source.setPassword(environment.getProperty("db.password"));
        return source;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(Objects.requireNonNull
                (managerFactoryBean().getObject()));
    }

    public Properties properties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect",environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
