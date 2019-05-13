package ua.footballdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "ua.footballdata" })
//public class FootballDataRestApiApplication extends SpringBootServletInitializer{
public class FootballDataRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballDataRestApiApplication.class, args);
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(FootballDataRestApiApplication.class); }
	 */

}
