package server;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.config.AppConfiguration;
import server.data.model.Movie;
import server.db.MovieCollectionDAO;
import server.db.MovieDAO;
import server.healthcheck.AppHealthCheck;
import server.resources.MovieResource;
import server.service.MovieService;

/**
 * Main application
 */
public class MainApplication extends Application<AppConfiguration>
{
	final static Logger logger = LoggerFactory.getLogger(MainApplication.class);


	public static void main(String[] args) throws Exception
	{
        new MainApplication().run(args);
    }

    @Override
    public String getName()
	{
        return "Test Server";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap)
	{
        // framework bootstrap initialization
		bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception
	{
		try
		{
			logger.info("Starting...");

			// application initialization goes here
			//hbm implementation
			final MovieService movieService = new MovieDAO(hibernateBundle.getSessionFactory());
			//collection implementation
			//final MovieService movieService = new MovieCollectionDAO();
			environment.jersey().register(new MovieResource(movieService));
		}
		catch (Exception exc)
		{
			// log failure to set up app
			logger.error("Failed to initialize application, exiting...", exc);
			throw new RuntimeException();
		}

		environment.healthChecks().register("app", new AppHealthCheck());

        // register servlet route handlers
		// environment.jersey().register(new YourServlet());
    }

	private final HibernateBundle<AppConfiguration> hibernateBundle =
			new HibernateBundle<AppConfiguration>(Movie.class) {
				@Override
				public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
					return configuration.getDataSourceFactory();
				}

			};
}
