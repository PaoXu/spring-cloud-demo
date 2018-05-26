package demo;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableDiscoveryClient
public class ResourceApplication {

	@RequestMapping("/")
	public Message home() {
		return new Message("(Resource) Hello World 000");
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

//	@Bean
//	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
//	    return factory.getUserInfoRestTemplate();
//	}
	
//	@Configuration
//	protected static class a extends ResourceServerConfigurerAdapter{
//
//		@Override
//		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//			
//			
//		}
//
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			// TODO Auto-generated method stub
//			http.authorizeRequests().anyRequest().authenticated();
//			//super.configure(http);
//		}
//		
//		
//	}
}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}