# TMDB-TheMovieDb-SpringBoot-App
TMDB(The Movie Db)-Java-Spring Boot-application

This TMDB application mainly focuses on consuming the TMDB api to perform the below operations in our Spring Boot REST. 

In this application implemented APIs to Show (Use below local urls to test these)
1. currently running Movies. (http://localhost:8080/tmdb/now-playing-movies)
2. All movies by Popularity. (http://localhost:8080/tmdb/allMoviesOrderByPopularity)
3. User to filter Movies by Rating. (http://localhost:8080/tmdb/moviesAboveRating?rating=100)
4. User to filter Movies by genresId. (http://localhost:8080/tmdb/movies?genresId=80)
5. Displaying one movie details by movieId. (http://localhost:8080/tmdb/movies?genresId=80)

In all the above operations we are displaying below details of Movie.

a. Movie Title

b. Movie genres

c. Movie Rating

d. Movie genre_ids


Tech Stack Used:-

Java1.8, 
Spring Boot 2.3.1.RELEASE, 
Spring REST, 
JSON, 
JUnit5.


This project is using TMDb API (https://www.themoviedb.org/documentation/api). 
To use TMDB API, we need API_KEY. To get this Key, we have to create account in this site. 
once Login goto Profile->Settings->API->fill all details and submit. It will generate API_KEY.

Steps to test the Application:

Download the source code.
Import it into STS IDE.
Build the application.
Run the application as Spring Boot application.
Use the above mentioned local endpoints to test respective operation.

