package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(CustomerServiceConfiguration.class)
//@EnableCircuitBreaker

//@EnableResourceServer  //protect API resources with an OAuth2 token
public class CustomerServiceApplication {

//	@Value("${lucky-word}") String luckyWord;//value from remote config server
//	
//	@Autowired
//	private ClientApplicationConfiguration configuration;
//	
    @RequestMapping("/")
    public String home() {
    	return "(Customer-Service) Hello World @@@###";
//        return "Hello World ===" + configuration.getLuckyWord();
    }
//
////    @Value("${info}") String info;//value from remote config server
////    @RequestMapping("/info")
////    public String info() {    	
////        //return info;
////    	return configuration.getInfo();
////    }
//
//    @RequestMapping("/demo")
//    public ModelAndView freemarkerDemo() {
//        return new ModelAndView("demo");
//    }
//    
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}

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
//    }
//    
//    @RequestMapping("/service-url/{applicationName}")
//    public String serviceUrl(@PathVariable String applicationName) {
//        List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
//        if (list != null && list.size() > 0 ) {
//            return list.get(0).getUri().toString();
//        }
//        return null;
//    }
//}


