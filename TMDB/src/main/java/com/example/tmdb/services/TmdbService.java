package com.example.tmdb.services;

import java.util.List;

import com.example.tmdb.model.Movie;

public interface TmdbService {

	Movie getMovieById(String movieById);

	List<Movie> getAllNowPlayingMovies();

	List<Movie> moviesOrderByPopularity();

	List<Movie> moviesAboveSpecifiedRating(int rating);

	List<Movie> moviesWithGivenGenresId(int genresId);

}
