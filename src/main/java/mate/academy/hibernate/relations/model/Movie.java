package mate.academy.hibernate.relations.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "movies")
public class Movie implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    public Movie() {}
    public Movie(String title) { this.title = title; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }

    @Override
    public Movie clone() {
        try {
            Movie clone = (Movie) super.clone();
            clone.actors = new ArrayList<>();
            for (Actor a : actors) {
                clone.actors.add(a.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't clone Movie", e);
        }
    }

    @Override
    public String toString() {
        return "Movie{id=" + id + ", title='" + title + "', actors=" + actors + "}";
    }
}
