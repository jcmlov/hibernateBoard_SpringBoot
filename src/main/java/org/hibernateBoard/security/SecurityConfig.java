package org.hibernateBoard.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.hibernateBoard.security.handler.CustomLoginSuccessHandler;
import org.hibernateBoard.security.social.SocialService;
import org.hibernateBoard.security.social.facebook.FacebookOAuth2ClientAuthenticationProcessingFilter;
import org.hibernateBoard.security.social.google.GoogleOAuth2ClientAuthenticationProcessingFilter;
import org.hibernateBoard.security.social.kakao.KakaoOAuth2ClientAuthenticationProcessingFilter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CompositeFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@EnableOAuth2Client
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private OAuth2ClientContext oauth2ClientContext;
	private SocialService socialService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers(
						"/css/**",
						"/script/**",
						"/image/**",
						"/fonts/**",
						"/lib/**",
						"/images/**",
						"/webjars/**",
						"/**/favicon.ico");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

		http
			.authorizeRequests()
			.antMatchers("/", "/login/loginForm").permitAll()
			.antMatchers("/main").hasAnyRole("ROLE_ADMIN, ROLE_USER")
			.antMatchers("/oauth").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest()
			.authenticated().and().exceptionHandling()
			.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and()
			.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);

		http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }
    
    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao() {
        return new ClientResources();
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(google(), new GoogleOAuth2ClientAuthenticationProcessingFilter(socialService)));
        filters.add(ssoFilter(facebook(), new FacebookOAuth2ClientAuthenticationProcessingFilter(socialService)));
        filters.add(ssoFilter(kakao(), new KakaoOAuth2ClientAuthenticationProcessingFilter(socialService)));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, OAuth2ClientAuthenticationProcessingFilter filter) {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
        filter.setTokenServices(tokenServices);
        filter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler("/oauth"));
        tokenServices.setRestTemplate(restTemplate);
        return filter;
    }
	
}
