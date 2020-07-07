package com.example.tmdb.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tmdb.model.Movie;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@SpringBootTest
public class TmdbServiceImplTest {

	@InjectMocks
	TmdbServiceImpl tmdbService;

	private JSONObject jsonObj = null;

	@BeforeEach
	public void before() throws Exception {
		readMovieById550JSONFile();
	}

	@Test
	public void testParseResponse() throws Exception {

		Movie movie = tmdbService.parseResponse(jsonObj);

		Assertions.assertEquals("Fight Club", movie.title);
		Assertions.assertEquals("Drama", movie.genres);
		Assertions.assertEquals(8.4, movie.rating);

	}

	@Test
	public void testParseResponse_jsonObjNull() throws Exception {

		Movie movie = tmdbService.parseResponse(new JSONObject());

		Assertions.assertEquals("", movie.title);
		Assertions.assertEquals("", movie.genres);
		Assertions.assertEquals(0.0, movie.rating);

	}

	private void readMovieById550JSONFile() throws ParseException {
		String fileName = "movieById550.json";
		readJsonDataFromFile(fileName);
	}

	private void readJsonDataFromFile(String fileName) throws ParseException {
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(fileName)) {

			jsonObj = (JSONObject) parser.parse(reader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseResponseToGetAllMovies() throws Exception {
		readJsonDataFromFile("AllNowPlayingMovies.json");
		List<Movie> movies = tmdbService.parseResponseToGetAllMovies(jsonObj);
		Assertions.assertTrue(movies.size() > 0);
	}
}
