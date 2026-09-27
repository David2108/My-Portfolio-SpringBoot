package com.portfolio.my_portfolio_backend.config;

import com.portfolio.my_portfolio_backend.service.IUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final IUserDetailService userDetailService;

    /**
     * Se usa para configurar que rutas necesitan autorización.
     * - /** -> Indica todas las rutas.
     * <p>
     * SecurityFilterChain -> Es un flujo de trabajo, es decir, es como una politica de seguridad
     * la cual se aplica a todas las peticiones que llegan a la aplicación.
     *
     * @return
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/education",
                                        "/experience",
                                        "/skills",
                                        "/projects",
                                        "/personal-info").authenticated()
                                .requestMatchers("/education/new",
                                        "/education/save",
                                        "/education/edit/**",
                                        "/education/delete/**").authenticated()
                                .requestMatchers("/experience/new",
                                        "/experience/save",
                                        "/experience/edit/**",
                                        "/experience/delete/**").authenticated()
                                .requestMatchers("/skills/new",
                                        "/skills/save",
                                        "/skills/edit/**",
                                        "/skills/delete/**").authenticated()
                                .requestMatchers("/personal-info/create",
                                        "/personal-info/edit/**",
                                        "/personal-info/save").authenticated()
                                .requestMatchers("/education/personal/**",
                                        "/experience/personal/**",
                                        "/skills/personal/**").authenticated()
                                .requestMatchers("/projects/new-project",
                                        "/projects/save").authenticated()
                                .anyRequest().permitAll()
                )
                // Habilita el formulario de Login
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
                // Se indica la ruta del controller del formulario
                .formLogin(form ->
                        form.loginPage("/login").permitAll()
                )
                .logout(longout -> longout
                        .logoutUrl("/logout")
                        // Indica que la peticion /logout es de tipo GET, no es recomendable se debe usar con un método POST
//                        .logoutRequestMatcher(request ->
//                                "GET".equalsIgnoreCase(request.getMethod()) &&
//                                        "/logout".equalsIgnoreCase(request.getRequestURI())
//                        )
                        .logoutSuccessUrl("/login?logout") // Ruta a donde se redirije cuando se cierre sesion
                        .invalidateHttpSession(true) // Invalida la sesion
                        .deleteCookies("JSESSIONID") // Elimina las cookies
                        .permitAll()
                );
        return http.build();
    }

    /**
     * Configura un usuario en memoria
     *
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Encriptar la contraseña
     * - Se implementa
     * - Se usa: passwordEncoder().encode("admin")
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
