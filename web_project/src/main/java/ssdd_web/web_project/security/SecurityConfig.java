package ssdd_web.web_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import ssdd_web.web_project.security.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final RepositoryUserDetailsService userDetailsService;

        public SecurityConfig(RepositoryUserDetailsService userDetailsService) {
                this.userDetailsService = userDetailsService;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder());
                return authProvider;
        }

        @Bean
        @Order(2)
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/js/**", "/images/**",
                                                                "/pictures/**")
                                                .permitAll()
                                                .requestMatchers("/home", "/users/register", "/users/add",
                                                                "/players/list", "/teams/list", "/matches/list",
                                                                "/tournaments/list")
                                                .permitAll()
                                                .requestMatchers(
                                                                "/profile", "/players/{id}", "/teams/{id}",
                                                                "/matches/{id}", "/tournaments/{id}", "/teams/add",
                                                                "/teams/register", "/users/delete/{id}",
                                                                "/teams/delete/{id}", "/players/{id}/image")
                                                .hasRole("USER")
                                                .requestMatchers("/players/**", "/teams/**", "/users/**", "/matches/**",
                                                                "/tournaments/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/users/login")
                                                .failureUrl("/loginerror")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/home")
                                                .permitAll())
                                .exceptionHandling(ex -> ex
                                                .accessDeniedPage("/error"));

                return http.build();
        }

        // (BCrypt)
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        @Order(1)
        public SecurityFilterChain apiFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter)
                        throws Exception {
                                http
                                .securityMatcher("/api/**") // only /api/ path
                                .csrf(csrf -> csrf.disable()) // CSRF not necessary for JWT
                                .authorizeHttpRequests(auth -> auth
                                                // Rutas públicas
                                                .requestMatchers("/api/auth/**").permitAll() // Login / refresh / logout
                                                .requestMatchers("/api/players/paged", // Páginas de jugadores
                                                                "/api/teams/paged", // Páginas de equipos
                                                                "/api/matches/paged", // Páginas de partidos
                                                                "/api/tournaments/paged", // Páginas de torneos
                                                                "/api/users/paged") // Páginas de usuarios
                                                .permitAll()
                                                .requestMatchers("/api/players/{id}", 
                                                                "/api/teams/{id}", 
                                                                "/api/matches/{id}", 
                                                                "/api/tournaments/{id}", 
                                                                "/api/users/{id}",
                                                                "/api/players/",
                                                                "/api/matches",
                                                                "/api/teams/",
                                                                "/api/tournaments") 
                                                .hasRole("USER")
                                                .requestMatchers("/api/players/**", 
                                                                "/api/teams/**",
                                                                "/api/matches", 
                                                                "/api/matches/**", 
                                                                "/api/tournaments/**", 
                                                                "/api/users/**") 
                                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session
                                               
                                )
                                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Filtro
                                                                                                                // JWT

                return http.build();
        }

}