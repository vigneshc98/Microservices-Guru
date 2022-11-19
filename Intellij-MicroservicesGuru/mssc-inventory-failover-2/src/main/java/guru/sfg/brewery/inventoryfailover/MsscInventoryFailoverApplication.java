package guru.sfg.brewery.inventoryfailover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsscInventoryFailoverApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(MsscInventoryFailoverApplication.class, args);
		}catch (Exception e){
			System.out.println(e);
		}
	}

}
