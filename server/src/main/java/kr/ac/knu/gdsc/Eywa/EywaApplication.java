package kr.ac.knu.gdsc.Eywa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EywaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EywaApplication.class, args);
	}
}
