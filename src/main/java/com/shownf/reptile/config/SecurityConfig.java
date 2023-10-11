package com.shownf.reptile.config;

import org.springframework.context.annotation.Configuration;


@Configuration
/*@EnableWebSecurity*/
public class SecurityConfig{
/*

    @Autowired private CustomFilterBean customFilterBean;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                */
/*.antMatchers("/", "/**").hasRole("ADMIN")*//*

                .antMatchers("/login/oauth2/**").permitAll()
                .anyRequest().authenticated();
        //http.addFilterBefore(customFilterBean, BasicAuthenticationFilter.class);
    }
*/

}