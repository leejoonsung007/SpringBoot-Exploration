package com.mastering.springboot.config;

import com.mastering.springboot.model.User;
import com.mastering.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception{
        //ignore Spring Security for URL /h2-console/ and all of its sub-URLs
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/**")
                    .hasRole("USER"); //the user needs to be authenticated and anonymous users will not be allowed to access anything

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    // insert some users into the database at startup
    public ApplicationRunner applicationRunner(){
        return args -> {
            userDetailService.create(new User( null,"shazin", passwordEncoder().encode("password"), "ROLE_USER"));
            userDetailService.create(new User(null,"shahim", passwordEncoder().encode("password"), "ROLE_USER"));
        };
    }

}
