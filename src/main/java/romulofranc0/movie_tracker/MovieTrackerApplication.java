package romulofranc0.movie_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTrackerApplication.class, args);
	}

}
