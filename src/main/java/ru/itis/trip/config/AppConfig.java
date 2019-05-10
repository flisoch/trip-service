package ru.itis.trip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "ru.itis.trip.repositories")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(value = "ru.itis.trip")
@PropertySource("classpath:application.properties")
@Import(WebAppInitializer.class)
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver resolver
                = new UrlBasedViewResolver();
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("driver.classname")));
        dataSource.setUrl(environment.getProperty("datasourse.url"));
        dataSource.setUsername(environment.getProperty("datasourse.username"));
        dataSource.setPassword(environment.getProperty("datasourse.password"));
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ru.itis.trip.entities");
        factory.setDataSource(dataSource);
        factory.setJpaDialect(jpaDialect());
        factory.setJpaProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        String[] propsNames = {"hibernate.hbm2ddl.auto", "hibernate.dialect",
                "hibernate.show_sql"};
        Arrays.stream(propsNames).forEach(propName ->
                hibernateProperties.setProperty(propName,
                        environment.getProperty(propName)));
        return hibernateProperties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
