package com.example.weatherAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherApiApplication {
	private static final String APIkey = System.getenv("APIkey");

	public static void main(String[] args) {
		// SpringApplication.run(WeatherApiApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=" + APIkey;

		String jsonResponse = restTemplate.getForObject(url, String.class);
		System.out.println(jsonResponse);
	}

}
