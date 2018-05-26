package demo.ribbon;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



/**
 * 
1. install the Customer service
cd /home/xwp/ws-spring-boot/spring-cloud-service-customer
mvn clean package
mvn install

2. start up the first Customer Service instance
cd /home/tools/maven/repository/demo/spring-cloud-service-customer/1.0-SNAPSHOT/
java -jar  -Dspring.application.name=customer-service -Dserver.port=8081 -Dmanagement.port=54001 -DluckyWord=8081-instance spring-cloud-service-customer-1.0-SNAPSHOT.jar 

3. start up the second Customer Service instance
cd /home/tools/maven/repository/demo/spring-cloud-service-customer/1.0-SNAPSHOT/
java -jar  -Dspring.application.name=customer-service -Dserver.port=8082 -Dmanagement.port=54002 -DluckyWord=8082-instance spring-cloud-service-customer-1.0-SNAPSHOT.jar 

4. start up the client
mvn spring-boot:run

5. curl -X GET -i http://localhost:8080/ribbon/greeting/John
1) 
Hello John:) [China] Welcome to customer service : 8082-instance
2) 
Hello John:) [China] Welcome to customer service : 8081-instance

 *
 */
@RestController
@RequestMapping("/ribbon")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private LoadBalancerClient loadBalancer;
	
	@Autowired
	RestTemplate restTemplate;
	
    @RequestMapping(value="/greeting/{customerId}")
    public String greeting(@PathVariable String customerId) {
    	
    	ServiceInstance instance = loadBalancer.choose("customer-service");    	    	
    	
    	String greetingUrl = instance.getUri().toString() + "/customer/greeting?name={name}&region={region}";
    	logger.info("@@@@Cusotmer service URI : {}",greetingUrl);
    	
    	Map<String,String> uriVariables = new HashMap<String,String>();
    	
    	uriVariables.put("name", customerId);  
    	uriVariables.put("region", "China");
    	 
    	
    	return restTemplate.getForObject(greetingUrl, String.class, uriVariables);
    }
    
}


