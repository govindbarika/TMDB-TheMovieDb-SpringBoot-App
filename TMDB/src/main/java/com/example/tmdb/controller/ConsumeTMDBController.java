package com.example.tmdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tmdb.model.Movie;
import com.example.tmdb.services.TmdbService;

@RestController
@RequestMapping("/tmdb")
public class ConsumeTMDBController {

	@Autowired
	private TmdbService tmdbService;

	@GetMapping(value = "/movies/{movieById}") // http://localhost:8080/tmdb/movies/550
	public Movie fetchMovieDetailsByMovieId(@PathVariable String movieById) throws Exception {

		return tmdbService.getMovieById(movieById);

	}

	@GetMapping(value = "/now-playing-movies") // http://localhost:8080/tmdb/now-playing-movies
	public List<Movie> getAllNowPlayingMovies() {

		return tmdbService.getAllNowPlayingMovies();
	}

	@GetMapping(value = "/allMoviesOrderByPopularity") // http://localhost:8080/tmdb/allMoviesOrderByPopularity
	public List<Movie> moviesOrderByPopularity() {

		return tmdbService.moviesOrderByPopularity();
	}

	@GetMapping(value = "/moviesAboveRating") // http://localhost:8080/tmdb/moviesAboveRating?rating=100
	public List<Movie> moviesAboveSpecifiedRating(@RequestParam int rating) {

		return tmdbService.moviesAboveSpecifiedRating(rating);
	}

	@GetMapping(value = "/movies") // http://localhost:8080/tmdb/movies?genresId=80
	public List<Movie> moviesWithGivenGenresId(@RequestParam int genresId) {

		return tmdbService.moviesWithGivenGenresId(genresId);
	}
}
