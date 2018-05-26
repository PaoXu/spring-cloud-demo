package demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties
@Getter @Setter
public class ZuulApplicationConfiguration {

	private String luckyWord;
	private String info;

}
