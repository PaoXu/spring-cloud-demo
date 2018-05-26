package demo;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@Configuration
@EnableAuthorizationServer
public	class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

   	Logger logger = LoggerFactory.getLogger(getClass());
   	
		@Autowired
		private AuthenticationManager authenticationManager;

	    
//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter() {
//			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			KeyPair keyPair = new KeyStoreKeyFactory(
//					new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//					.getKeyPair("test");
//			converter.setKeyPair(keyPair);
//			return converter;
//		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			logger.info("***************configure oauth2 client***************");
			//Memory client for demo 
			clients.inMemory()
					.withClient("zuulgateway")
					.secret("zuulsecret")
					.autoApprove(true) //without showing the approval screen to user
					.authorizedGrantTypes("authorization_code", "refresh_token","password")
					.scopes("openid");
			
			//TODO: load client from data source
			//clients.jdbc(dataSource)
			
			//TODO: load client from service
			//clients.withClientDetails(clientDetailsService)
		}


//		@Autowired
//	    private DataSource dataSource;
//		
//	    @Bean 
//	    @Primary
//	    public TokenStore tokenStore() {
//	        return new JdbcTokenStore(dataSource);
//	    }
//	    
//	    @Bean
//	    @Primary
//	    public ClientDetailsService clientDetailsService() {
//	        return new JdbcClientDetailsService(dataSource);
//	    }
	    
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)	throws Exception {
			//endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());

			endpoints.authenticationManager(authenticationManager);
//			endpoints.tokenStore(tokenStore());
//			endpoints.setClientDetailsService(clientDetailsService());
//
//			DefaultTokenServices tokenServices = new DefaultTokenServices();
//			tokenServices.setTokenStore(endpoints.getTokenStore());
//	        tokenServices.setSupportRefreshToken(true);
//	        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//	        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//	        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.MINUTES.toSeconds(10));
//	        
//			endpoints.tokenServices(tokenServices);
//			
		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//			oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		}

   }
