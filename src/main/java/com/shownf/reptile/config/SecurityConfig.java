package com.shownf.reptile.config;

import com.shownf.reptile.bean.CustomFilterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private CustomFilterBean customFilterBean;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login/oauth2/**", "/", "/test/**","/chat-event", "/connect/**").permitAll() // 로그인, health 체크
                .antMatchers("/user/**/name", "/user/**/image", "/user/**/token").permitAll()// 유저 이름, 이미지 정보
                .antMatchers(HttpMethod.GET, "/user/{userId}").permitAll()
                .antMatchers(HttpMethod.GET, "/postMeta/**").permitAll()
                .antMatchers("/swagger-ui.html","swagger/**","/webjars/**", "/swagger-resources/**", "/v2/api-docs").permitAll() // 스웨거
                .antMatchers(HttpMethod.GET, "/image", "/comment/**", "/post/**", "/reply/**", "/sale/**").permitAll() // 스와이프
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated();
        http.addFilterBefore(customFilterBean, BasicAuthenticationFilter.class);
    }

}