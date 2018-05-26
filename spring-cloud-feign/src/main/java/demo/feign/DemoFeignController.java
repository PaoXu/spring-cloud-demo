package demo.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 1. start up Customer Service instance
 * /home/xwp/ws-spring-boot/spring-cloud-service-customer/run.txt
 * 
 * 
 * 2. curl -X GET -i 'http://localhost:8085/feign/greeting/John?region=UK'
 * Feign-fallback
 * 
 * 3. curl -X GET -i 'http://localhost:8085/feign/greeting/John?region=China'
 * Hello John:) [China] Welcome to customer service : （Customer-Service) Greeting My Own
 * 
 */
@RestController
@RequestMapping("/feign")
public class DemoFeignController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/greeting/{customerId}")
	public String greeting(@PathVariable String customerId, @RequestParam("region") String region) {
		return customerService.geeting(customerId, region);
	}
}
