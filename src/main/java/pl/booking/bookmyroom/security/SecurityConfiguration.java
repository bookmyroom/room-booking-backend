package pl.booking.bookmyroom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import pl.booking.bookmyroom.security.model.ActiveUserStore;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/hotels").permitAll()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers(HttpMethod.GET, "/user/all").permitAll()
                .antMatchers(HttpMethod.POST, "/corporations/register").permitAll()
                .antMatchers(HttpMethod.POST, "/corporations/login").permitAll()
                .antMatchers(HttpMethod.GET, "/corporations/all").permitAll()
                .antMatchers(HttpMethod.POST, "/reservation").permitAll()
                .antMatchers(HttpMethod.PATCH, "/reservations/status").permitAll()
                .antMatchers(HttpMethod.GET, "/loggedUsers").permitAll()
                .antMatchers("/logged").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
        .and().formLogin().loginPage("/user/login").permitAll();


        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(2);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}