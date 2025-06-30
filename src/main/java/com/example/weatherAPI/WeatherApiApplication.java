package com.example.weatherAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
		String jsonResponse = restTemplate.getForObject(url, String.class, city);
		//System.out.println(jsonResponse);

		// Display temperature, description, and humidity
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(jsonResponse);
			JsonNode weatherArr = rootNode.path("weather");
			JsonNode description = weatherArr.get(0).path("description");
			JsonNode cityName = rootNode.path("name");
			JsonNode temperature = rootNode.path("main").path("temp");
			JsonNode humidity = rootNode.path("main").path("humidity");

			System.out.println("City: " + cityName.asText());
			System.out.println("Description: " + description.asText());
			System.out.println("Temperature: " + temperature.asInt() + "F");
			System.out.println("Humidity: " + humidity.asInt() + "%");

		} catch (Exception e) {
			System.out.println("JSON parsing error.");
		}

	}

}
