package com.example.gestionEncheres.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.gestionEncheres.repository")
public class JpaConfig {
}
