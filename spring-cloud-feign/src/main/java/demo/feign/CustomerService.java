package demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="customer-service",configuration=CustomerServiceConfiguration.class,fallback=CustomerServiceFallback.class)
public interface CustomerService {

	@RequestMapping(value="/customer/greeting",method=RequestMethod.GET)
	public String geeting(@RequestParam("name") String name, @RequestParam("region") String region);
	
}