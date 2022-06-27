package ted.talks.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "ted.talks.rest", "ted.talks.dao" })
public class TedTalksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TedTalksApplication.class, args);
	}
}
