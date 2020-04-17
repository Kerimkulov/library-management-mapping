//package kz.iitu.librarymanagement.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@ComponentScan(basePackages = "kz.iitu.librarymanagement")
////@PropertySource("application.properties")
//public class SpringConfiguration {
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/library_management");
//        dataSource.setPassword("123");
//        dataSource.setUsername("postgres");
//        return dataSource;
//    }
//}
