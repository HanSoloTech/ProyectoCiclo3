package com.co.hansolo.tecnology.security;

import com.co.hansolo.tecnology.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password,state from employees  where email =?")
                .authoritiesByUsernameQuery("select email, role from employees where email =?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()


                .antMatchers( "/","/enterprises/**").hasRole("ADMIN")
                .antMatchers("/employee/**").hasRole("ADMIN")
                .antMatchers("/movements","/transaction/crear", "/transaction/editar/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/Denegado")
                .and().logout().permitAll();

                /* .anyRequest().permitAll()
                .and().formLogin().permitAll()
                .and().logout().permitAll();*/
    }
}
