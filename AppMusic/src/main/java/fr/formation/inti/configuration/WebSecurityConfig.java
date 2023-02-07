package fr.formation.inti.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new CustomUserDetailsService();
	    }
	     
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }

	 
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();//csrf ??

			// The pages does not require login
			http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout", "register").permitAll();

			// /userInfo page requires login as USER or ADMIN.
			// If no login, it will redirect to /login page.
			http.authorizeRequests().antMatchers("/listusers").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

			// For ADMIN only.
			http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOM')");
			http.authorizeRequests().antMatchers("/employees").access("hasRole('ROLE_ADMIN')");
//			http.authorizeRequests()
//			 .antMatchers("/resources/**").permitAll()
//	         .antMatchers("/*.css").permitAll();

			// When the user has logged in as XX.
			// But access a page that requires role YY,
			// AccessDeniedException will throw.
			http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

			// Config for Login Form
			http.authorizeRequests().and().formLogin()//
					// Submit URL of login page.
					.loginProcessingUrl("/j_spring_security_check") // Submit URL
					.loginPage("/login")//
					.defaultSuccessUrl("/listusers")//
					.failureUrl("/login?error=true")//
					.usernameParameter("username")// 
					.passwordParameter("password")
					// Config for Logout Page
					.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutsuccessful");

		}
	     
}
