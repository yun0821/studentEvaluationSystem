package com.cy.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限管理配置信息
 *
 * @author Cy
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    public void setMyFilterSecurityInterceptor(MyFilterSecurityInterceptor myFilterSecurityInterceptor) {
        this.myFilterSecurityInterceptor = myFilterSecurityInterceptor;
    }

    @Bean
    UserDetailsService customUserService() { //注册UserDetailsService 的bean
        return new CustomUserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(MemoryVariableUtils.STATIC_HIGH_ACCOUNT)
//                .password(MemoryVariableUtils.STATIC_HIGH_ACCOUNT_P).roles("ADMIN");
//        auth.userDetailsService(customUserService()).passwordEncoder(new MyPasswordEncoder());
    }

    /**
     * FilterSecurityInterceptor拦截器做配置
     *
     * @param http http 请求
     * @throws Exception 异常
     * @author Cy
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用CSRF保护
                .csrf().disable()
                .authorizeRequests()
                //任何访问都必须授权
//        .anyRequest().fullyAuthenticated()
                //配置那些路径可以不用权限访问
                .antMatchers("/index.html", "/index.html#/login").permitAll()
                .and()
                .formLogin()
                //登陆成功后的处理，因为是API的形式所以不用跳转页面
                .successHandler(new RestAuthenticationSuccessHandler())
                //登陆失败后的处理
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                //登出后的处理
                .logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
                .and()
                //认证不通过后的处理
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.headers().frameOptions().sameOrigin();//disable();  svg图片拦截器
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     * 登陆成功后的处理
     */
    public static class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication) {
            System.out.println("-------登录成功-----------");
            clearAuthenticationAttributes(request);
        }
    }

    /**
     * 登出成功后的处理
     */
    public static class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication) {
        }
    }

    /**
     * 权限不通过的处理
     */
    public static class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request,
                             HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            System.out.println("-----------权限不通过的处理----------");
            String uri = request.getRequestURI().trim();
            Cookie[] cookies = request.getCookies();
            String loginStr = "login";
            String indexHtmlStr = "index.html";
            String jsessionid = "JSESSIONID";
            if (cookies == null) {
                if (!uri.contains(loginStr) && !uri.contains(indexHtmlStr)) {
                    response.sendRedirect("/index.html#/login");
                }
            } else if (cookies.length == 1 && jsessionid.equals(cookies[0].getName())) {
                response.sendRedirect("/index.html#/login");
            }
        }
    }
}
