package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(ZuulApplicationConfiguration.class)
@EnableZuulProxy
//public class ZuulApplication{
//	@Autowired
//	private ZuulApplicationConfiguration configuration;
//	
//    @RequestMapping("/home")
//    public String home() {
////    	return "Hello World @@@###" + luckyWord;
//        return "(Zuul) Hello World ===" + configuration.getLuckyWord();
//    }
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(ZuulApplication.class, args);
//    }
//}

@EnableOAuth2Sso
public class ZuulApplication extends WebSecurityConfigurerAdapter {

//	@Value("${lucky-word}") String luckyWord;//value from remote config server
	
	@Autowired
	private ZuulApplicationConfiguration configuration;
	
    @RequestMapping("/home")
    public String home() {
//    	return "Hello World @@@###" + luckyWord;
        return "(Zuul) Hello World ===" + configuration.getLuckyWord();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 // @formatter:off
      http
          .logout().logoutSuccessUrl("/").and()
          .authorizeRequests()
          		.antMatchers("/index.html", "/home", "/").permitAll()
          		.anyRequest().authenticated()
          		.and()
          .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      // @formatter:on
    }
    
    @Bean
    protected OAuth2RestTemplate OAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
      return new OAuth2RestTemplate(resource, context);
    }
    
//    @Bean
//    public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
//        return factory.getUserInfoRestTemplate();
//    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}

