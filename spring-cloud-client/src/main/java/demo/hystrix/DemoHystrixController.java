package demo.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class DemoHystrixController {

	@Autowired
	private DemoHystrixService service;
		
	//http://localhost:54000/hystrix.stream
	//http://localhost:8080/hystrix/greeting/hystrix
    @RequestMapping(value="/greeting/{id}",method=RequestMethod.GET)
    public String greeting(@PathVariable String id) throws Exception{
    	
    	return service.getMessage(id);
    	
    }
    
    //http://localhost:8080/hystrix/timeout/hystrix
    //http://localhost:8080/hystrix/timeout/hello
    @RequestMapping(value="/timeout/{id}",method=RequestMethod.GET)
    public String timeout(@PathVariable String id) throws Exception{
    	
    	return service.timeoutMessage(id);    	
    }
}
