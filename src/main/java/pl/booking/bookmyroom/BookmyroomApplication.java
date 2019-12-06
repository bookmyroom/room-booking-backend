package pl.booking.bookmyroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.booking.bookmyroom.user.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class BookmyroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmyroomApplication.class, args);
	}

}
