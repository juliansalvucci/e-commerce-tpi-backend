package tpi.backend.e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //Configuración para que el swagger no solicite login.
public class ECommerceApplication {

	public static void main(String[] args) { 
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
