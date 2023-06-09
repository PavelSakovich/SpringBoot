package com.example.securityConfig;



import com.example.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/security/login", "/error").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, "/user/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password("admin")
                        .roles(Role.ADMIN.name())
                        .build(),
                User.builder()
                        .username("user")
                        .password("user")
                        .roles(Role.USER.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

