package ua.ak.sample.jpa.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import ua.ak.sample.jpa.service.OneService;
import ua.ak.sample.jpa.repository.TutorialRepository;

@Configuration
@EnableJpaRepositories(basePackages = "ua.ak.sample.jpa.repository"
,entityManagerFactoryRef = "akEntityManager", 
transactionManagerRef = "akTransactionManager")
class MyConfig
{
//	@Autowired
//	TutorialRepository repo;
	
    @Autowired
    private Environment env;
    
    @Primary
    @Bean
    @ConfigurationProperties(prefix="ak.datasource")
    public DataSource akDataSource() {
    	BasicDataSource basicDataSource=new BasicDataSource();    	
        return basicDataSource;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean akEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(akDataSource());
        em.setPackagesToScan(
          new String[] { "ua.ak.sample.jpa.model" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
          env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }
    
    @Bean
    public PlatformTransactionManager akTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          akEntityManager().getObject());
        return transactionManager;
    }
    
	@Bean
	public OneService oneService(TutorialRepository repo)
	{
		OneService serv=new OneService();
		serv.setTutorialRepository(repo);
		serv.setTransactionManager(akTransactionManager());
		return serv;
	}
}
