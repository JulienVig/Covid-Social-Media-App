package coronhackathon.backend.entity;

import sun.reflect.generics.tree.Tree;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Challenge {
    @Id
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

    @OneToMany(mappedBy = "challenge")
    Set<HasCompleted> completed = new HashSet<>();

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

    public Set<HasCompleted> getHasCompleted() {
        return new HashSet<HasCompleted>(this.completed);
    }


    /* REMOVE if unused
    NOTE: when using this method, do not explicitly add the symmetric Challenge to user.has_completed
    public void addHas_completed(User completer) {
        this.has_completed.add(completer);
        completer.getHas_completed().add(this);
    }
     */
}
