package apirestusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiRestUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestUsersApplication.class, args);
	}

}