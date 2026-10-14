
package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Main {

    private final JdbcTemplate db;

    public Main(JdbcTemplate db) {
        this.db = db;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String nombre) {

        // Vulnerabilidad intencional: SQL Injection
        String sql = "SELECT nombre FROM usuarios WHERE nombre = '"
                + nombre + "'";

        return db.queryForList(sql, String.class).toString();
    }
}
