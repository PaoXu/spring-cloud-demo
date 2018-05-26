package demo.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.CustomerServiceConfiguration;

@RestController
@RequestMapping("/customer")
public class CustomerServiceController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerServiceConfiguration configuration;
	
	
    @RequestMapping(value="/greeting/{name}")
    public String greeting(@PathVariable String name) {
    	return "Hello " + name +":) ["+"] Welcome to customer service : " + configuration.getLuckyWord();
    }
    
    @RequestMapping(value="/greeting")
    public String greeting(@RequestParam String name,@RequestParam String region) throws Exception{
    	
    	logger.info("customer greeting service is called.....region:{}",region);
    	
    	//simulate exception
    	if("UK".equals(region)){
    		throw new Exception("Unsupported region :" + region);
    	}
    	else if("US".equals(region)){
    		try{
    			Thread.sleep(2000L);
    		}catch(InterruptedException e){
    			logger.info("Wake up from sleep: " + region);
    		}
    	}
    	else if("UA".equals(region)){
    		try{
    			Thread.sleep(500L);
    		}catch(InterruptedException e){
    			logger.info("Wake up from sleep: " + region);
    		}
    	}
    	
    	return "Hello " + name +":) ["+region+"] Welcome to customer service : " + configuration.getLuckyWord();
    }
}
