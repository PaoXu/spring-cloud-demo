package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaServer
public class Application {

	@Value("${lucky-word}") String luckyWord;//value from remote config server
	
    @RequestMapping("/home")
    public String home() {
        return "Hello World@!@" + luckyWord;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}