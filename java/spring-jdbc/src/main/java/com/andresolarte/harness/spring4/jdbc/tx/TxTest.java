package com.andresolarte.harness.spring4.jdbc.tx;

import com.andresolarte.harness.spring4.jdbc.Constants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = TxTest.class)
public class TxTest {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxTest.class);
        TxApp app = context.getBean(TxApp.class);
        app.run();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Constants.H2_CLASS);
        dataSource.setUrl(Constants.H2_URL + Constants.CREATE_TABLE_DDL);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
