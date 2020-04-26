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
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/clients/signUp/**").permitAll()
                .antMatchers("/clients/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/books/availableBooks**").hasAnyAuthority("ADMIN", "MANAGER","USER")
                .antMatchers(HttpMethod.POST,"/books/**").hasAnyAuthority("MANAGER")
                .antMatchers("/books/{id}/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/clientBooks/{id}").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.DELETE, "/clientBooks//{bookId}/{clientId}").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, "/clientBooks//{bookId}/{clientId}").hasAnyAuthority("ADMIN", "MANAGER","USER")
                .antMatchers("/clientBooks").hasAnyAuthority("ADMIN","MANAGER")
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
