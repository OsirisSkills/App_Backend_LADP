package fr.jehann.app.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Vous pouvez spécifier un chemin plus précis si nécessaire
                .allowedOrigins("http://localhost:4200") // Vous pouvez spécifier les origines autorisées ici
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Vous pouvez spécifier les en-têtes autorisés ici
                .allowCredentials(true)
                .maxAge(3600); // Temps maximum en secondes pendant lequel les réponses pré-vol peuvent être mises en cache par les clients
    }
}
