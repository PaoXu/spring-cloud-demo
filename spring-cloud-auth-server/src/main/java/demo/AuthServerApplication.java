package demo;

import java.security.Principal;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



//https://blog.csdn.net/isea533/article/details/78078101
//https://blog.csdn.net/neosmith/article/details/52539927
//https://github.com/spring-guides/tut-spring-security-and-angular-js/blob/master/oauth2-vanilla/README.adoc
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthServerApplication extends WebMvcConfigurerAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
    
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
		//registry.addViewController("/oauth/error").setViewName("error");
    }
    
    
    @Bean(initMethod="createDataSource", destroyMethod="close")  
    @ConfigurationProperties(prefix = "spring.datasource")  
    public DataSource dataSource() {  
    	BasicDataSource dataSource = new BasicDataSource();    	
        return dataSource;          
    }  
    
    
    
    @Configuration
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	protected static class LoginConfig extends WebSecurityConfigurerAdapter {
		
//    	final Logger logger = LoggerFactory.getLogger(getClass());
    	
//		@Autowired
//		private AuthenticationManager authenticationManager;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.formLogin().loginPage("/login").permitAll()		
			.and()
				.requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
			.and()
				.authorizeRequests().anyRequest().authenticated();
			
			// @formatter:on
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.parentAuthenticationManager(authenticationManager);
//			auth.jdbcAuthentication()
//			auth.ldapAuthentication()

			//security.user.name: user
			//security.user.password: user
			auth.inMemoryAuthentication()
			.withUser("user").password("user").roles("USER")
			.and()
			.withUser("admin").password("admin").roles("ADMIN","USER");
		}
    }
}
