package com.example.tmdb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tmdb.exceptions.InvalidInputException;
import com.example.tmdb.model.Movie;
import com.example.tmdb.util.TMDBUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TmdbServiceImpl implements TmdbService {

	@Autowired
	private RestTemplate restTemplate;

	private static final String BASE_URI = "https://api.themoviedb.org/3/movie/";
	private static final String API_KEY = "?api_key=3506f2dab94547602a672580c3610e59";

	@Override
	public Movie getMovieById(String movieById) {
		// validate Input
		if (TMDBUtil.isValidNumber(movieById)) {
			String url = BASE_URI + movieById + API_KEY;
			Object response = restTemplate.getForObject(url, Object.class);
			return parseResponse(response);
		} else {
			throw new InvalidInputException("Please Enter only Number");
		}
	}

	@Override
	public List<Movie> getAllNowPlayingMovies() {
		String url = "https://api.themoviedb.org/3/discover/movie?api_key=3506f2dab94547602a672580c3610e59&language=en-US";
		Object response = restTemplate.getForObject(url, Object.class);
		return parseResponseToGetAllMovies(response);
	}

	@Override
	public List<Movie> moviesOrderByPopularity() {
		String url = "https://api.themoviedb.org/3/discover/movie?api_key=3506f2dab94547602a672580c3610e59&language=en-US&sort_by=popularity.desc";
		Object response = restTemplate.getForObject(url, Object.class);
		List<Movie> movies = parseResponseToGetAllMovies(response);
		movies = movies.stream().sorted((e1, e2) -> ((Double) e2.rating).compareTo((Double) e1.rating))
				.collect(Collectors.toList());
		return movies;
	}

	@Override
	public List<Movie> moviesAboveSpecifiedRating(int inputRating) {
		String url = "https://api.themoviedb.org/3/discover/movie?api_key=3506f2dab94547602a672580c3610e59&language=en-US";
		Object response = restTemplate.getForObject(url, Object.class);
		List<Movie> movies = parseResponseToGetAllMovies(response);
		movies = movies.stream().filter(movie -> movie.rating >= inputRating).sorted((e1, e2) -> ((Double) e2.rating).compareTo((Double) e1.rating)).collect(Collectors.toList());
		return movies;
	}

	@Override
	public List<Movie> moviesWithGivenGenresId(int inputGenresId) {
		String url = "https://api.themoviedb.org/3/discover/movie?api_key=3506f2dab94547602a672580c3610e59&language=en-US";
		Object response = restTemplate.getForObject(url, Object.class);
		List<Movie> movies = parseResponseToGetAllMovies(response);
		movies = movies.stream().filter(movie -> movie.genre_ids.contains(inputGenresId)).collect(Collectors.toList());
		return movies;
	}

	public List<Movie> parseResponseToGetAllMovies(Object response) {
		ObjectMapper obj = new ObjectMapper();
		String title = "";
		double rating = 0.0;
		List<Movie> nowPlayingMovies = new ArrayList<>();
		if (response != null) {
			try {
				String jsonString = obj.writeValueAsString(response);
				JSONObject jsonObj = new JSONObject(jsonString);
				JSONArray movies = new JSONArray(jsonObj.getString("results"));

				for (int i = 0; i < movies.length(); i++) {
					JSONObject movie = movies.getJSONObject(i);
					title = movie.getString("original_title");
					ArrayList<Integer> genreIds = new ArrayList<>();
					JSONArray jArray = ((JSONArray) movie.get("genre_ids"));
					if (jArray != null) {
						for (int j = 0; j < jArray.length(); j++) {
							genreIds.add(jArray.getInt(j));
						}
					}
					rating = movie.getDouble("popularity");
					System.out.println(genreIds);

					nowPlayingMovies.add(new Movie(title, rating, genreIds));

				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return nowPlayingMovies;
	}

	public Movie parseResponse(Object response) {
		ObjectMapper obj = new ObjectMapper();
		String title = "";
		String genres = "";
		double rating = 0.0;
		if (response != null) {
			try {
				String jsonString = obj.writeValueAsString(response);
				JSONObject jsonObj = new JSONObject(jsonString);
				title = jsonObj.getString("original_title");
				genres = new JSONArray(jsonObj.getString("genres")).getJSONObject(0).getString("name");
				rating = jsonObj.getDouble("popularity");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return new Movie(title, genres, rating);
	}

}
