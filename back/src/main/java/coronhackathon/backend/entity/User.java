package coronhackathon.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany
    @JoinTable(
            name = "hasCompleted",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_id"))
    Set<Challenge> completedChallenges;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
