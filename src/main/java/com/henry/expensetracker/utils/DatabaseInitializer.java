package com.henry.expensetracker.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_TABLE_CATEGORY = """
        CREATE TABLE IF NOT EXISTS category (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            description VARCHAR(255) NOT NULL
        )
        """;

    private static final String CREATE_TABLE_USER = """
            CREATE TABLE IF NOT EXISTS users (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE NOT NULL
            )
        """;

    private static final String CREATE_TABLE_EXPENSE = """
        CREATE TABLE IF NOT EXISTS expense (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            id_user BIGINT NOT NULL,
            id_category BIGINT NOT NULL,
            amount DOUBLE NOT NULL,
            date DATE NOT NULL,
            category VARCHAR(255) NOT NULL,
            description TEXT,
            FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
            FOREIGN KEY (id_category) REFERENCES category(id) ON DELETE CASCADE
            )
        """;

        @PostConstruct
        public void initializeDatabase() {
            jdbcTemplate.execute(CREATE_TABLE_CATEGORY);
            jdbcTemplate.execute(CREATE_TABLE_USER);
            jdbcTemplate.execute(CREATE_TABLE_EXPENSE);
            System.out.println("✅ Tablas creadas o ya existen.");
        }

}
