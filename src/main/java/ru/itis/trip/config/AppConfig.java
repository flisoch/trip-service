package ru.itis.trip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.itis.trip")
@PropertySource("classpath:application.properties")
@Import(WebAppInitializer.class)
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/templates/");
        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("driver.classname")));
        dataSource.setUrl(environment.getProperty("driver.url"));
        dataSource.setUsername(environment.getProperty("driver.username"));
        dataSource.setPassword(environment.getProperty("driver.password"));
        return dataSource;
    }

  /*  @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(dataSource());
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
