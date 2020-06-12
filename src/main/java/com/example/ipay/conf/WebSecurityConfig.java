package com.example.ipay.conf;


import com.example.ipay.interceptor.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true, securedEnabled =true, jsr250Enabled =true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*    //密码加密函数
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123"))
//                .roles("admin");
//
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("user");
//    }



    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/SysUsers/login").permitAll()
                //放行swagger
                .antMatchers("/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**").permitAll()
//                .antMatchers("/**").permitAll()
                //
                .anyRequest().authenticated()//其他路径认证之后就可以访问
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}
