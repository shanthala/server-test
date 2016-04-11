package server.db;

import server.data.model.Movie;
import server.service.MovieService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MovieCollectionDAO implements MovieService {

    private Map<Long,Movie> movieMap = new ConcurrentHashMap<>();
    private AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public List<Movie> listMovies() {

        return movieMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public void createMovie(Movie movie) {
        Long movieId = atomicLong.incrementAndGet();
        movie.setId(movieId);
        movieMap.put(movieId,movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieMap.put(movie.getId(),movie);
    }

    @Override
    public void removeMovie(Movie movie) {
        movieMap.remove(movie.getId());

    }
}
