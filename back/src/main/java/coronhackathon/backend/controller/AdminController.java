package coronhackathon.backend.controller;

import com.sun.jmx.remote.internal.ArrayQueue;
import coronhackathon.backend.entity.Category;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.repository.CompletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    CompletedRepository completedRepository;
    @RequestMapping(path = "/admin/notApprovedCompleted", method = RequestMethod.GET)
    public List<CompletedDTO> getNotApprovedCompleted() {
        List<HasCompleted> lhc =  completedRepository.findByApproved(true);
        List<CompletedDTO> lc = new ArrayList<>();
        for(HasCompleted hc : lhc)
            lc.add(new CompletedDTO(hc));

        return lc;
    }

    private class CompletedDTO{
        private long userId;
        private String username;
        private long challengeId;
        private String challengeName;
        private String commentary;
        private String picture;

        private CompletedDTO(HasCompleted hc){
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
}