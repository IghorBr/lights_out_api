package iw.graph.lights_out.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class ResourceServerConfiguration {

    @Bean
    public SecurityFilterChain resourceFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                        request
                            .requestMatchers("/swagger-ui/**", "/swagger-ui/oauth2-redirect.html", "/v3/api-docs/**").permitAll()
                            .anyRequest().authenticated()
                )
                .csrf().disable()
                .cors()
                .and()
                .oauth2ResourceServer().jwt();

        return http.build();
    }
}
