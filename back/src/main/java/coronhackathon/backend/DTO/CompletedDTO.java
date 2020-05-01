package coronhackathon.backend.DTO;

import coronhackathon.backend.entity.HasCompleted;

public class CompletedDTO {
    private long userId;
    private String username;
    private long challengeId;
    private String challengeName;
    private String commentary;
    private String picture;

    public CompletedDTO(HasCompleted hc){
        this.userId = hc.getUser().getId();
        this.username = hc.getUser().getUsername();
        this.challengeId = hc.getChallenge().getId();
        this.challengeName = hc.getChallenge().getName();
        this.commentary = hc.getCommentary();
        this.picture = hc.getPicture();
    }

    public String getUsername() {
        return username;
    }

    public long getUserId() {
        return userId;
    }

    public String getCommentary() {
        return commentary;
    }

    public String getPicture() {
        return picture;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }
}
