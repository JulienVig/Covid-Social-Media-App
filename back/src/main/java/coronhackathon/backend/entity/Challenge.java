package coronhackathon.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Challenge {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    private String logo;     //the link to the logo

    /* ---- Relations ---- */

    @ManyToMany(mappedBy = "has_completed")
    private Set<User> has_completed = new HashSet<User>();

    @ManyToMany
    @JoinTable(
            name = "is_A",
            joinColumns = @JoinColumn(name = "challenge_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> is_A;

    /* ----Getters and Setters---- */

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLogo() {
        return logo;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Collection<User> getHas_completed() {
        return this.has_completed;
    }

    public void setHas_completed(Set<User> has_completed) {
        this.has_completed = has_completed;
    }

    /* REMOVE if unused
    NOTE: when using this method, do not explicitly add the symmetric Challenge to user.has_completed
    public void addHas_completed(User completer) {
        this.has_completed.add(completer);
        completer.getHas_completed().add(this);
    }
     */
}
