package com.example.clothesshop.config;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.UserDetailsImpl;
import com.example.clothesshop.filter.CustomAuthenticationFilter;
import com.example.clothesshop.filter.CustomAuthorizationFilter;
//import com.example.clothesshop.service.impl.UserDetailsServiceImpl;
import com.example.clothesshop.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//    private final CorsConfiguration config;
//    private static final String[] USER_URLS = {
//            "/api/user/**"
//    };
    private static final String[] PUBLIC_URLS = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/**",
            "/login**",
            "/api/login",
            "/api/register"
    };
    private static final String[] ADMIN_URLS = {
            "/api/admin/**"
    };
    private static final String[] IS_LOGIN_URLS = {
            "/user"
    };
    @Autowired
    UserDetailsService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthorizationFilter authenticationJwtTokenFilter() {
        return new CustomAuthorizationFilter();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors().and().csrf().disable();
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests()
                .antMatchers(ADMIN_URLS).hasAnyAuthority(SystemConstant.ADMIN_ROLE)
//                .antMatchers(USER_URLS).hasAnyAuthority(SystemConstant.USER_ROLE)
//                .antMatchers(IS_LOGIN_URLS).hasAnyAuthority(SystemConstant.ADMIN_ROLE, SystemConstant.USER_ROLE)
                .antMatchers(IS_LOGIN_URLS).permitAll()
//                .antMatchers(IS_LOGIN_URLS).authenticated()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .loginProcessingUrl("/doLogin")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.sendRedirect("/user");
//                        String name = authentication.getName();
//                        System.out.println("Logger in user: " + name);
//                        UserDetailsImpl userDetails = (UserDetailsImpl) userService.loadUserByUsername(name);
//                        UsernamePasswordAuthenticationToken authenticationToken = new
//                                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//                        SecurityContextHolder.getContext().setAuthentication(user);
//                        Authentication test = SecurityContextHolder.getContext().getAuthentication();
//                        System.out.println("test: " + test.getPrincipal().toString());
//                    }
//                })
                .defaultSuccessUrl("/user", true)
                .and()
                    .rememberMe()
                .and()
                    .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
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

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final var configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(config.getAllowedOrigins());
//        configuration.setAllowedMethods(asList("GET", "POST", "PUT", "PATCH", "DELETE"));
//        configuration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CACHE_CONTROL, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
//        final var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/**", configuration);
//        return source;
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").authorities(SystemConstant.ADMIN_ROLE);
//    }
}
