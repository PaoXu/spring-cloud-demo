package demo.feign;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceFallback implements CustomerService {

	//feign.hystrix.enabled=true
	@Override
	public String geeting(String name, String region) {
		
		return "Feign-fallback";
	}

}
