package com.studevs.practice.practice01;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@RestController
public class Main implements Serializable {
	
	private final ApplicationContext context;
	
	public static void main(String[] args) {
		log.info("Starting application");
		SpringApplication.run(Main.class, args);
		log.info("Started application");
	}
	
	@GetMapping(value = "/")
	public String hello() {
		log.debug("Calling: com.studevs.practice.practice01.Main.hello");
		return new Date().toString();
	}
}