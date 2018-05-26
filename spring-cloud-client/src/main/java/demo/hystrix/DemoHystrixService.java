package demo.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class DemoHystrixService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Get message from external store
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@HystrixCommand(fallbackMethod="defaultMessage")
	public String getMessage (String id) throws Exception{		
		
		if(id.startsWith("hystrix")){
			throw new Exception("Demo - Circuit Breaker");
		}
		
        return "Hello World - hystrix";
	}
	
	@HystrixCommand(fallbackMethod="defaultMessage", commandProperties={
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500")
	})
	public String timeoutMessage (String id) throws Exception{		
		
		if(id.startsWith("hystrix")){
			logger.info("Sleeping.....");
			//sleep 1000 ms to timeout
			Thread.currentThread().sleep(1000L);
		}
		
        return "Hello World - Timeout";
	}
	
	/**
	 * Store message locally for recovery
	 * @param content
	 * @return
	 */
	public String defaultMessage (String id){
		return "Circuit Breaker";
	}
}
