package demo;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



//https://blog.csdn.net/isea533/article/details/78078101
//https://blog.csdn.net/neosmith/article/details/52539927
//https://github.com/spring-guides/tut-spring-security-and-angular-js/blob/master/oauth2-vanilla/README.adoc
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAuthorizationServer //AuthorizationServerConfigurerAdapter.class
//public class AuthServerApplication extends WebMvcConfigurerAdapter {
//	public static void main(String[] args) {
//	  SpringApplication.run(AuthServerApplication.class, args);
//	}
//	
//  @Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//  }
//}
@SessionAttributes("authorizationRequest")

@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthServerApplication extends WebMvcConfigurerAdapter {
//
////	@Value("${lucky-word}") String luckyWord;//value from remote config server
//	
////	@Autowired
////	private AuthServerConfiguration configuration;
////	
////    @RequestMapping("/home")
////    public String home() {
//////    	return "Hello World @@@###" + luckyWord;
////        return "Hello World ===" + configuration.getLuckyWord();
////    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
    
//    @Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//    }
//    
//    @Configuration
//	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class LoginConfig extends WebSecurityConfigurerAdapter {
//		
//		@Autowired
//		private AuthenticationManager authenticationManager;
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http
//				.formLogin().loginPage("/login").permitAll()
//			.and()
//				.requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
//			.and()
//				.authorizeRequests().anyRequest().authenticated();
//			
//			// @formatter:on
//		}
//		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.parentAuthenticationManager(authenticationManager);
//		}
//    }
//    
//    @Configuration
//	@EnableAuthorizationServer
//	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//		@Autowired
//		private AuthenticationManager authenticationManager;
//
//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter() {
//			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			KeyPair keyPair = new KeyStoreKeyFactory(
//					new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//					.getKeyPair("test");
//			converter.setKeyPair(keyPair);
//			return converter;
//		}

//		@Override
//		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//			clients.inMemory()
//					.withClient("acme")
//					.secret("acmesecret")
//					.authorizedGrantTypes("authorization_code", "refresh_token","password")
//					.scopes("openid");
//		}

//		@Override
//		public void configure(AuthorizationServerEndpointsConfigurer endpoints)	throws Exception {
//			endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
//		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//			oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		}
//
//    }
}



