package com.PigeonSkyRace.Pigeon;

import org.springframework.boot.SpringApplication;

public class TestPigeonApplication {

	public static void main(String[] args) {
		SpringApplication.from(PigeonApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
