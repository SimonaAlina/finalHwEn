package config;

import com.endava.wiki.config.AppConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppConfigTest {

    @Mock
    AppConfig appConfig;

    @Test
    public void propertyPlaceholderConfigurer() throws Exception {
        when(appConfig.propertyPlaceholderConfigurer())
                .thenReturn(new PropertyPlaceholderConfigurer());
    }

    @Test
    public void dataSource() throws Exception {
        when(appConfig.dataSource(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(new BasicDataSource());
    }

    @Test
    public void entityManagerFactory() throws Exception {

        when(appConfig.entityManagerFactory(any(DataSource.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(new LocalContainerEntityManagerFactoryBean());

    }

    @Test
    public void transactionManager() throws Exception {
        when(appConfig.transactionManager(any(EntityManagerFactory.class)))
                .thenReturn(new JpaTransactionManager());
    }

    @Test
    public void viewResolver() throws Exception {
        when(appConfig.viewResolver())
                .thenReturn(new InternalResourceViewResolver());
    }

    @Test
    public void servletMultipartResolver() throws Exception {
        when(appConfig.servletMultipartResolver())
                .thenReturn(new StandardServletMultipartResolver());
    }

}