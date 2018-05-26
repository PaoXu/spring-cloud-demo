package demo.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
public class DemoRetryController {

	@Autowired
	private DemoRetryService service;
	
	//http://localhost:8080/retry/greeting/retry?maxAttampts=4
    @RequestMapping(value="/greeting/{message}",method=RequestMethod.GET)
    public String greeting(@PathVariable String message,@RequestParam("maxAttampts") int maxAttampts) throws Exception{
    	
    	if(maxAttampts > 0){
    		return service.send(message,maxAttampts);
    	}
    	
    	return service.send(message);
    }
    
}
