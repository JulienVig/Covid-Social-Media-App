package coronhackathon.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String pwdHash;

    /* ---- Relations ---- */

    @ManyToMany
    @JoinTable(
            name = "hasCompleted",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_id"))
    private Set<Challenge> has_completed = new HashSet<Challenge>();

    /* ----Getters and Setters---- */

    public long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public Set<Challenge> getHas_completed() {
        return has_completed;
    }

    public void setHas_completed(Set<Challenge> has_completed) {
        this.has_completed = has_completed;
    }

    /* REMOVE if unused
    NOTE: when using this method, do not explicitly add the symmetric User to challenge.has_completed
    public void addHas_completed(Challenge completedChallenge) {
        this.has_completed.add(completedChallenge);
        completedChallenge.getHas_completed().add(this);
    }
     */

}
