package demo.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableRetry
public class DemoRetryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public DemoRetryService() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Demo for RetryTemplate
	 * @param message
	 * @param maxAttempts
	 * @return
	 * @throws Exception
	 */
	public String send(String message, int maxAttempts) throws Exception{
		RetryTemplate retryTemplate = new RetryTemplate();
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(maxAttempts);
		
		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		
		String status = retryTemplate.execute(new RetryCallback<String,Exception>(){
			@Override
			public String doWithRetry(RetryContext context) throws Exception {
				logger.info("Retry to send message, count : {}", context.getRetryCount());
				return sendMessage(message);
			}	
		}, new RecoveryCallback<String>(){
			@Override
			public String recover(RetryContext context) throws Exception {
				logger.info("After retry : {}, recover",context.getRetryCount());
				return storeMessage(message);
			}				
		});
		
		//logger.info("Message Send status :{}",status);
		return status;
	}

	/**
	 * Send message to external 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	private String sendMessage (String content) throws Exception{
		logger.info("Retry to send message...");
		if(content.startsWith("retry")){
			throw new Exception("Demo - Retry Exception");
		}
		logger.info("Message is sent succesfully, content :{}",content);
        return "Retry Success";
	}
	
	/**
	 * Store message locally for recovery
	 * @param content
	 * @return
	 */
	private String storeMessage (String content){		
		logger.info("Message is stored locally, content :{}",content);
        return "Recover Success";
	}
	
	@Retryable(value=Exception.class, maxAttempts=3, backoff=@Backoff(value=0L))
	public String send(String message) throws Exception {
		return sendMessage(message);
	}	
	@Recover
	public String recoverSend(Exception e,String message) {
		return storeMessage(message);
	}
}
