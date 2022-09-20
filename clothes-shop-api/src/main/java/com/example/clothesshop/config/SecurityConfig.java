package com.example.clothesshop.config;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.filter.CustomAuthenticationFilter;
import com.example.clothesshop.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String[] PUBLIC_URLS = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/**"
    };
    private static final String[] ADMIN_URLS = {
            "/api/admin/**"
    };
    private static final String[] USER_URLS = {
            "/api/user/**"
    };
    private static final String[] SWAGGER_URLS = {
            "/swagger-ui.html"
    };
    private static final String[] IS_LOGIN_URLS = {
            "/user**"
    };

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
//        http.authorizeRequests().antMatchers("/api/**", "/api/login/**").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.antMatcher("/swagger-ui.html").authorizeRequests().anyRequest().hasAnyAuthority(SystemConstant.ADMIN_ROLE).and().httpBasic();
//        http.authorizeRequests().antMatchers("/v2/api-docs").authenticated().and().httpBasic();
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/api/**", "/api/login/**").permitAll()
//                .and().authorizeRequests().antMatchers("/api/admin/**", "/swagger-ui.html").hasAnyAuthority(SystemConstant.ADMIN_ROLE).authenticated()
//                .and().authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority(SystemConstant.USER_ROLE)
//                .and().authorizeRequests().antMatchers("/swagger-ui.html")
//                .hasAnyAuthority(SystemConstant.ADMIN_ROLE).and().httpBasic();


//        http.authorizeRequests()
//                .antMatchers("/api/admin/**").hasAnyAuthority(SystemConstant.ADMIN_ROLE)
//                .antMatchers("/api/user/**").hasAnyAuthority(SystemConstant.USER_ROLE)
//                .antMatchers("/swagger-ui.html").hasAnyAuthority(SystemConstant.ADMIN_ROLE)
//                .anyRequest().permitAll()
//                .and().httpBasic();


        http.authorizeRequests()
                .antMatchers(ADMIN_URLS).hasAnyAuthority(SystemConstant.ADMIN_ROLE)
//                .antMatchers(IS_LOGIN_URLS).hasAnyAuthority(SystemConstant.ADMIN_ROLE, SystemConstant.USER_ROLE)
//                .antMatchers(USER_URLS).hasAnyAuthority(SystemConstant.USER_ROLE)
                .antMatchers(IS_LOGIN_URLS).permitAll()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
//                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/user", true)
                    .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().permitAll()
                .and();
//        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").authorities(SystemConstant.ADMIN_ROLE);
//    }
}
