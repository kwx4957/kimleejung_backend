package com.capstone.kimleejung.config;

import com.capstone.kimleejung.security.JwtAuthenticationFilter;
import com.capstone.kimleejung.security.OAuthSuccessHandler;
import com.capstone.kimleejung.security.OAuthUserServiceImpl;
import com.capstone.kimleejung.security.RedirectUrlCookieFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                             OAuthUserServiceImpl oAuthUserService,
                             OAuthSuccessHandler oAuthSuccessHandler,
                             RedirectUrlCookieFilter redirectUrlFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.oAuthUserService = oAuthUserService;
        this.oAuthSuccessHandler = oAuthSuccessHandler;
        this.redirectUrlFilter = redirectUrlFilter;
    }

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuthUserServiceImpl oAuthUserService;
    private final OAuthSuccessHandler oAuthSuccessHandler; // Success Handler 추가
    private final RedirectUrlCookieFilter redirectUrlFilter;

    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http.cors()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/oauth2/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .authorizationEndpoint()
                .baseUri("/auth/authorize") // OAuth 2.0 흐름 시작을 위한 엔드포인트 추가
                .and()
                .userInfoEndpoint()
                .userService(oAuthUserService)
                .and()
                .successHandler(oAuthSuccessHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint()); // Http403ForbiddenEntryPoint 추가

        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
        http.addFilterBefore( // Before
                redirectUrlFilter,
                OAuth2AuthorizationRequestRedirectFilter.class // 리디렉트가 되기 전에 필터를 실행해야 한다.
        );
    }
}