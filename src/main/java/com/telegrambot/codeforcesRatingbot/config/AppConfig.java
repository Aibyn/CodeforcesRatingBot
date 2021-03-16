package com.telegrambot.codeforcesRatingbot.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        DataSourceBuilder basicDataSource = DataSourceBuilder.create();
        basicDataSource.driverClassName("org.postgresql.Driver");
        basicDataSource.url(dbUrl);
        basicDataSource.username(username);
        basicDataSource.password(password);

        return basicDataSource.build();
    }

}
