package net.emapp.webfluxsecurity;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@Profile("dev")
public class WebFluxSecurityApplication {

	public static void main(String[] args) {
//		SpringApplication.run(WebFluxSecurityApplication.class, args);
		SpringApplication application = new SpringApplication(WebFluxSecurityApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.setAdditionalProfiles("dev");
		application.run(args);
	}

}
