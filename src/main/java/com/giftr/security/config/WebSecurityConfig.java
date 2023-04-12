package com.giftr.security.config;

import com.giftr.appuser.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
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
            .csrf().disable() // TODO: remove when only using form based login
            .authorizeHttpRequests(auth -> {
                try {
                    auth
                        .requestMatchers("/", "/error", "/h2/**", "/api/v*/**",
                                "/api/v*/registration/**", "/confirmed")
                            .anonymous()
                        .anyRequest()
                            .authenticated()
                            .and()
                            .formLogin()
                            .defaultSuccessUrl("/home");
                } catch (Exception e) {
                    LOGGER.error("An error occurred using the security filter chain: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            })

            .authenticationProvider(daoAuthenticationProvider()
            );
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
