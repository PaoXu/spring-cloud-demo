package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Request;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(ClientApplicationConfiguration.class)
//@EnableCircuitBreaker
//@EnableHystrixDashboard
//@EnableTurbine
@EnableFeignClients
public class ClientApplication {

//	@Value("${lucky-word}") String luckyWord;//value from remote config server
	
	@Autowired
	private ClientApplicationConfiguration configuration;
	
    @RequestMapping("/")
    public String home() {
//    	return "Hello World @@@###" + luckyWord;
        return "Hello World ===" + configuration.getLuckyWord();
    }

//    @Value("${info}") String info;//value from remote config server
//    @RequestMapping("/info")
//    public String info() {    	
//        //return info;
//    	return configuration.getInfo();
//    }

//    @RequestMapping("/demo")
//    public ModelAndView freemarkerDemo() {
//        return new ModelAndView("demo");
//    }
//    
//    @Bean
//    public RestTemplate restTemplate() {
//    	return new RestTemplate();
//    }
    
    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 6000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 3000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
    
//    @Bean
//    Retryer feignRetryer() {
//        return Retryer.NEVER_RETRY;	
//    }
    
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
    
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
//
//
//@RestController
//class ServiceInstanceRestController {
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping("/service-instances/{applicationName}")
//    public List<ServiceInstance> serviceInstancesByApplicationName(
//            @PathVariable String applicationName) {
//    	
//    	ServiceInstance s = this.discoveryClient.getInstances(applicationName).get(0);
//    	
//        return this.discoveryClient.getInstances(applicationName);
//    }ne
//        List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
//        if (list != null && list.size() > 0 ) {
//            return list.get(0).getUri().toString();
//        }
//        return null;
//    }
//}


