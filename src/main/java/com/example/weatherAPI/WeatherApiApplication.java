package com.example.weatherAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class WeatherApiApplication {
	private static final String APIkey = System.getenv("APIkey");

	public static void main(String[] args) {
		// SpringApplication.run(WeatherApiApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.openweathermap.org/data/2.5/weather?q={city}&units=imperial&appid=" + APIkey;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the weather app!");
		System.out.print("Enter a city name: ");
		String city = scanner.nextLine();

		// Display temperature, description, and humidity
		try {
			WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class, city);
			System.out.println("City: " + response.getName());
			System.out.println("Description: " + response.getWeather().getFirst().getDescription());
			System.out.println("Temperature: " + response.getMain().getTemp() + "F");
			System.out.println("Feels like: " + response.getMain().getFeels_like() + "F");
			System.out.println("Humidity: " + response.getMain().getHumidity() + "%");
		} catch (HttpClientErrorException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}
