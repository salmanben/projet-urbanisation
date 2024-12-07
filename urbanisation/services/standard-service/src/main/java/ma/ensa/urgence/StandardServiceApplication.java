package ma.ensa.urgence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StandardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandardServiceApplication.class, args);
		System.out.println("Standard Service is running!!!!!!!!");
	}

}
