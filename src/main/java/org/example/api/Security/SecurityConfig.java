package org.example.api.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF si no lo necesitas
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/empleado").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/empleado/{nombre}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/empleado/{nombre}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/categoria").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/categoria/{nombre}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/producto").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/producto/{nombre}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/mesa").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/mesa/{nombre}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/resena").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/resena/empleado/").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/resena").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/orden").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/orden").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/orden/mesa/").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/orden/mesa/").permitAll()
                        // Requiere autenticación para el resto de las rutas
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); // Añadir JwtAuthenticationFilter

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCryptPasswordEncoder para la codificación y verificación de contraseñas
    }
}
