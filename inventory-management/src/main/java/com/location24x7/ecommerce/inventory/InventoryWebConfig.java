package com.location24x7.ecommerce.inventory;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.location24x7.ecommerce.inventory"})
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySources(@PropertySource(value = {"classpath:application.properties"}))
public class InventoryWebConfig {

    private static final String[] ENTITY_PACKAGES = {"com.location24x7.ecommerce.inventory.model"};

    private static final String DB_DRIVER_CLASS = "db.driver";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.username";
    private static final String DIALECT = "hibernate.dialect";
    private static final String FORMAT_SQL = "hibernate.format_sql";
    private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String SHOW_SQL = "hibernate.show_sql";

    @Bean
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DB_DRIVER_CLASS));
        dataSource.setUrl(env.getRequiredProperty(DB_URL));
        dataSource.setUsername(env.getRequiredProperty(DB_USER));
        dataSource.setPassword(env.getRequiredProperty(DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
        Properties jpaProperties = new Properties();
        jpaProperties.put(DIALECT, env.getRequiredProperty(DIALECT));
        jpaProperties.put(HBM2DDL_AUTO, env.getRequiredProperty(HBM2DDL_AUTO));
        jpaProperties.put(NAMING_STRATEGY, env.getRequiredProperty(NAMING_STRATEGY));
        jpaProperties.put(SHOW_SQL, env.getRequiredProperty(SHOW_SQL));
        jpaProperties.put(FORMAT_SQL, env.getRequiredProperty(FORMAT_SQL));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
