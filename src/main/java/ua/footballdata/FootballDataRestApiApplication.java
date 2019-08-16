package ua.footballdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = { "ua.footballdata" })

public class FootballDataRestApiApplication extends SpringBootServletInitializer {

	/*
	 * @Bean public ModelMapper modelMapper() { ModelMapper mapper = new
	 * ModelMapper();
	 * mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).
	 * setFieldMatchingEnabled(true)
	 * .setSkipNullEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE); return
	 * mapper; }
	 */

	public static void main(String[] args) {
		SpringApplication.run(FootballDataRestApiApplication.class, args);
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(FootballDataRestApiApplication.class); }
	 */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FootballDataRestApiApplication.class);
	}

	@Override
	protected WebApplicationContext run(SpringApplication application) {
		return super.run(application);
	}

}
