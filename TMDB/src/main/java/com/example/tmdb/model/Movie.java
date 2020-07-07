package com.example.tmdb.model;

import java.util.List;

public class Movie {

	public String title;
	public String genres;
	public double rating;
	public List<Integer> genre_ids;

	public Movie() {
		super();
	}

	public Movie(String title, String genres, double rating) {
		super();
		this.title = title;
		this.genres = genres;
		this.rating = rating;
	}

	public Movie(String title, double rating, List<Integer> genre_ids) {
		super();
		this.title = title;
		this.rating = rating;
		this.genre_ids = genre_ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<Integer> getGenre_ids() {
		return genre_ids;
	}

	public void setGenre_ids(List<Integer> genre_ids) {
		this.genre_ids = genre_ids;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", genres=" + genres + ", rating=" + rating + ", genre_ids=" + genre_ids + "]";
	}

}
