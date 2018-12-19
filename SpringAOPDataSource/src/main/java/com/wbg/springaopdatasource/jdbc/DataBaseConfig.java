package com.wbg.springaopdatasource.jdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.wbg.springaopdatasource.jdbc"})
public class DataBaseConfig {
    @Bean
    public static JDBCAspect getJDBCAspect(){
        return new JDBCAspect();
    }
}
