package guru.sfg.msscbrewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsscBeerService2Application {

	public static void main(String[] args) {
		SpringApplication.run(MsscBeerService2Application.class, args);
	}

}
