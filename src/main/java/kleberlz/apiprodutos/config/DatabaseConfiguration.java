package kleberlz.apiprodutos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseConfiguration { // Dados do application.yml
	
	@Value("${spring.datasource.url}")
	String url;
	
	@Value("${spring.datasource.username}")
	String username;
	
	@Value("${spring.datasource.password}")
	String password;
	
	@Value("${spring.datasource.driver-class-name}")
	String driver;
	
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driver);
		return datasource;
	}
	
	@Bean
	public DataSource hikariDataSource() {
		
		log.info("Iniciando conexão com o banco na URL: {}", url);
		
		HikariConfig config = new HikariConfig();
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		
		config.setMaximumPoolSize(10);// maximo de conexões permitidas
		config.setMinimumIdle(1);//tamanho inicial da pool
		config.setPoolName("produtos-db-pool");
		config.setMaxLifetime(600000);
		config.setConnectionTimeout(100000);//timeout para conseguir uma conexão
		config.setConnectionTestQuery("select 1");//query(consulta) de teste.
		
		return new HikariDataSource(config);
	}
}

