package server.resources;
import io.dropwizard.hibernate.UnitOfWork;
import server.data.model.Movie;
import server.db.MovieDAO;
import server.service.MovieService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
    private MovieService movieService;

    public MovieResource(MovieService movieService){
        this.movieService=movieService;
    }

    @GET
    @UnitOfWork
    @Path("/movie/list")
    public List<Movie> getAllMovies(){
        return movieService.listMovies();
    }

    @POST
    @UnitOfWork
    @Path("/movie/create")
    public void addMovie(Movie movie){
        movieService.createMovie(movie);
    }

    @POST
    @UnitOfWork
    @Path("/movie/update")
    public void updateMovie(Movie movie){
        movieService.updateMovie(movie);
    }

    @POST
    @UnitOfWork
    @Path("/movie/delete")
    public void removeMovie(Movie movie){
        movieService.removeMovie(movie);
    }

    @GET
    @Path("/timeOfDay")
    //@Produces(MediaType.TEXT_PLAIN)
    public String getDate() {
        return dtFormatter.format(LocalTime.now());
    }
}
