package io.github.forezp.examples;

import io.github.forezp.examples.aop.FieldPostProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamplesApplication.class, args);
	}

	@Autowired
	FieldPostProcessorService fieldPostProcessorService;

	@GetMapping("/test")
	public String test() {
		return fieldPostProcessorService.test();
	}
}
