package com.giftr.security.config;

import com.giftr.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AppUserService appUserService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeHttpRequests(auth -> {
                try {
                    auth
                        .requestMatchers("/*", "/error", "/h2/**", "/api/v*/**",
                                "/api/v*/registration/**", "/confirmed", "**/webjars/**",
                                "static/**")
                            .permitAll()
                        .anyRequest()
                            .authenticated();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .formLogin(form ->
                form
                    .loginPage("/login")
                    .permitAll())
            .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2/**"));
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
