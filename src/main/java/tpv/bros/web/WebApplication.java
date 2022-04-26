package tpv.bros.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	"tpv.core.configuration"
	, "tpv.bros.common.configuration"
	, "tpv.bros.common.service"
	, "tpv.bros.web.configuration"
	, "tpv.bros.web.controller"
})
public class WebApplication {
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(WebApplication.class, args);
	}
}
