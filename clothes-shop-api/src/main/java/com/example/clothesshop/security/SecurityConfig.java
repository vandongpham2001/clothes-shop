package com.example.clothesshop.security;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.filter.CustomAuthenticationFilter;
import com.example.clothesshop.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().antMatchers("/api/admin/**").hasAnyAuthority(SystemConstant.ADMIN_ROLE);
//        http.authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority(SystemConstant.USER_ROLE);
//        http.authorizeRequests().antMatchers("/api/**", "/api/login/**", "/api/token/refresh/**").permitAll();

        http.authorizeRequests().antMatchers("/api/**").permitAll();

//        http.authorizeRequests().anyRequest().permitAll();

/*
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority(SystemConstant.USER_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority(SystemConstant.USER_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/**").hasAnyAuthority(SystemConstant.ADMIN_ROLE);
*/

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}