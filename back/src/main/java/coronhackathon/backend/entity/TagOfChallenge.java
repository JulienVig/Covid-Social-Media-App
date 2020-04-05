package coronhackathon.backend.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class TagOfChallenge {
    @EmbeddedId()
    private IsAId id = new IsAId();

    @ManyToOne
    @MapsId("challengeId")
    private Challenge challenge;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

}
