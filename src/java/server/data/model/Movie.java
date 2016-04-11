package server.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "MOVIE")
@NamedQueries({
        @NamedQuery(
                name = "movie.findAll",
                query = "SELECT m FROM Movie m"
        )
})
public class Movie implements Serializable {

    private static final long serialVersionUID = -6413219472586478031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "GENRE", nullable = false)
    private String genre;
    @Column(name = "YEAR_RELEASED", nullable = false)
    private int yearReleased;
    @Column(name = "RATING", nullable = false)
    private int rating;

    public Movie(){}

    public Movie(long id, String name, String genre, int yearReleased, int rating){
        this.id=id;
        this.name=name;
        this.genre=genre;
        this.yearReleased=yearReleased;
        this.rating=rating;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        return id == movie.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", yearReleased=").append(yearReleased);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
