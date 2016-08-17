package com.endava.wiki.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.endava.wiki")
@EnableJpaRepositories(basePackages = "com.endava.wiki.repository")
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer x = new PropertyPlaceholderConfigurer();
        x.setLocation(new ClassPathResource("application.properties"));
        return x;
    }

    @Bean
    public DataSource dataSource(@Value("${mysqldb.className}") String className,
                                 @Value("${mysqldb.url}") String url,
                                 @Value("${mysqldb.user}") String user,
                                 @Value("${mysqldb.pass}") String pass) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(className);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Value("${hibernate.dialect}") String hDialect,
                                                                       @Value("${hibernate.show_sql}") String hShowSql,
                                                                       @Value("${hibernate.format_sql}") String hFormatSql) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.endava.wiki.entity");
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", hDialect);
        jpaProperties.setProperty("hibernate.show_sql", hShowSql);
        jpaProperties.setProperty("hibernate.format_sql", hFormatSql);
//        jpaProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        emf.setJpaProperties(jpaProperties);
        emf.setDataSource(dataSource);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
    public StandardServletMultipartResolver servletMultipartResolver(){
        StandardServletMultipartResolver multipartResolver =new StandardServletMultipartResolver();
        return multipartResolver;
    }
}
