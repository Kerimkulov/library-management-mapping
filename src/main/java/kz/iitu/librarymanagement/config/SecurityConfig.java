package kz.iitu.librarymanagement.config;

import kz.iitu.librarymanagement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/clients/signUp/**").permitAll()
                .antMatchers("/api/clients/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/api/books/availableBooks**").hasAnyAuthority("ADMIN", "MANAGER","USER")
                .antMatchers(HttpMethod.POST,"/api/books/**").hasAnyAuthority("MANAGER")
                .antMatchers("/api/books/{id}/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/clientBooks/{id}").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.DELETE, "/api/clientBooks/{bookId}/{clientId}").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, "/api/clientBooks/{bookId}/{clientId}").hasAnyAuthority("ADMIN", "MANAGER","USER")
                .antMatchers("/api/clientBooks").hasAnyAuthority("ADMIN","MANAGER")
                .anyRequest().authenticated() // Rest accesses available for all authorized users
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder((passwordEncoder()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
