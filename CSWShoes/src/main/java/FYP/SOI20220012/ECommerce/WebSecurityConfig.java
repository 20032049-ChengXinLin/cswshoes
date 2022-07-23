/**
*
* I declare that this code was written by me, 20032049.
* I will not copy or allow others to copy my code.
* I understand that copying code is considered as plagiarism.
*
* Student Name: Cheng Xin Lin (20032049), Koh Siew Gek (20008303), Chen Wan Ting (20009334)
* Team ID: SOI-2022-0012
* Team Project ID: SOI-2022-2210-0049
* Project: [IP] Digital Wallet
* Date created: 2022-May-05 4:08:37 pm
*
*/
package FYP.SOI20220012.ECommerce;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author 20032049
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public ShoesMemberDetailsService ShoesMemberDetailsService() {
		return new ShoesMemberDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder cswShoespasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider cswShoesauthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(ShoesMemberDetailsService());
		authProvider.setPasswordEncoder(cswShoespasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(cswShoesauthenticationProvider());
	}

	@Bean
	public PersistentTokenRepository cswShoespersistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
		tokenRepo.setDataSource(dataSource);
		return tokenRepo;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/pagetwo/*").permitAll()
			.antMatchers("/uploads/products/*/*").permitAll()
			.antMatchers("/bootstrap/*/*").permitAll()
			.antMatchers("/images/*", "/images/products/*/*").permitAll()
			.antMatchers("/font-awesome/*/*").permitAll()
			.antMatchers("/jquery/*").permitAll()
			.antMatchers("/uploads/products/*/*").permitAll()
			.antMatchers("/shoppingcart", "/shoppingcart/add/*", "/shoppingcart/edit/*", "/shoppingcart/delete/*",
				"/purchasehistory", "/transaction_successful/receipt").hasRole("CUSTOMER")
			.anyRequest().authenticated().and().formLogin()
			.loginPage("/login").permitAll()
			.and().logout()
			.logoutUrl("/logout").permitAll()
			.and().rememberMe()
			.tokenRepository(cswShoespersistentTokenRepository()).userDetailsService(ShoesMemberDetailsService()).and()
			.exceptionHandling().accessDeniedPage("/403");

	}

}
