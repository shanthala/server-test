package server.service;

import server.data.model.Movie;

import java.util.List;


public interface MovieService {

    public List<Movie> listMovies();
    public void createMovie(Movie movie);
    public void updateMovie(Movie movie);
    public void removeMovie(Movie movie);

}
