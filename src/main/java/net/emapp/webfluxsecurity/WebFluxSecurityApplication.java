package net.emapp.webfluxsecurity;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxSecurityApplication {

	public static void main(String[] args) {
//		SpringApplication.run(WebFluxSecurityApplication.class, args);
		SpringApplication application = new SpringApplication(WebFluxSecurityApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
//		application.setAdditionalProfiles("myprofile");
		application.run(args);
	}

}
