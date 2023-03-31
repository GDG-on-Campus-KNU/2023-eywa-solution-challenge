package kr.ac.knu.gdsc.Eywa.config;

import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final PrincipalDetailService principalDetailService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().disable();
    http.authorizeRequests()
        .anyRequest()
        .permitAll()
        .and()
        .oauth2Login()
        .defaultSuccessUrl("/members/session")
        .userInfoEndpoint()
        .userService(principalDetailService);
  }
}
