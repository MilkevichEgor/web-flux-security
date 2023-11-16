package net.emapp.webfluxsecurity;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Application entry point.
 */

@SpringBootApplication
public class WebFluxSecurityApplication {

	public static void main(String... args) throws IOException {


		System.out.println("Argument count: " + args.length);
		for (int i = 0; i < args.length; i++) {
			System.out.println("Argument " + i + ": " + args[i]);
		}


		SpringApplication application = new SpringApplication(WebFluxSecurityApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.setAdditionalProfiles("dev");
		application.run(args);
	}

}
