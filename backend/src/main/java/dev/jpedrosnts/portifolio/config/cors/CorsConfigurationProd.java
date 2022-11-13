package dev.jpedrosnts.portifolio.config.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod")
public class CorsConfigurationProd implements WebMvcConfigurer {

    private final String websiteOriginAllowed;

    @Autowired
    public CorsConfigurationProd(@Value("${website.origin.allowed}") String websiteOriginAllowed) {
        this.websiteOriginAllowed = websiteOriginAllowed;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET").allowedOriginPatterns(websiteOriginAllowed);
    }

}