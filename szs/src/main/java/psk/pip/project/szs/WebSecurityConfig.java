package psk.pip.project.szs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().permitAll().and().formLogin().loginProcessingUrl("/login")
				.usernameParameter("login").passwordParameter("password")
				.failureHandler(new SimpleUrlAuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1,
							AuthenticationException arg2) throws IOException, ServletException {
						super.onAuthenticationFailure(arg0, arg1, arg2);

						System.out.println("Authentication Failure");

					}
				}).successHandler(new SimpleUrlAuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
							Authentication arg2) throws IOException, ServletException {

						System.out.println("Authentication Success");

					}
				}).permitAll().and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {

					@Override
					public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
							AuthenticationException arg2) throws IOException, ServletException {
						arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED);

						System.out.println("Authentication Entry Point");

					}
				}).and().logout().permitAll().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandler() {

					@Override
					public void onLogoutSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
							throws IOException, ServletException {

						System.out.println("Logout success");
					}
				}).and().headers().frameOptions().disable().and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT login, password, active FROM user WHERE login = ?")
				.authoritiesByUsernameQuery(
						"SELECT u.login, r.name rola from user u inner join user_roles on u.id = user_roles.user_id inner join role r on r.id = user_roles.roles_id WHERE u.login = ?")
				.passwordEncoder(new BCryptPasswordEncoder());

	}
}