package server.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import server.data.model.Movie;
import server.service.MovieService;

import java.util.List;


public class MovieDAO  extends AbstractDAO<Movie> implements MovieService {


    public MovieDAO(SessionFactory sessionFactoryFactory){
        super(sessionFactoryFactory);
    }


    @Override
    public List<Movie> listMovies() {
        return list(namedQuery("movie.findAll"));
    }

    @Override
    public void createMovie(Movie movie) {
        currentSession().save(movie);
    }

    @Override
    public void updateMovie(Movie movie){ currentSession().update(movie);}

    @Override
    public void removeMovie(Movie movie) {
        currentSession().delete(movie);
    }
}
