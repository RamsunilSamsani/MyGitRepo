package in.nic.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class SpringBootMiniProj1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProj1Application.class, args);
	}

}
