package coronhackathon.backend.service;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.repository.ChallengeRepository;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompletedService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;


    public List<Challenge> getCompletedChallenges(long userId) {
        List<Challenge> l = new ArrayList<Challenge>();
        Optional<User> ou = userRepository.findById(userId);
        if(!ou.isPresent())
            return l;
        for(HasCompleted hc : ou.get().getHasCompleted()){
            l.add(hc.getChallenge());
        }
        return l;
    }

    public List<User> getCompletersOfChallenge(long challengeId) {
        List<User> l = new ArrayList<User>();
        Optional<Challenge> oc = challengeRepository.findById(challengeId);
        if(!oc.isPresent())
            return l;
        for(HasCompleted hc : oc.get().getHasCompleted()){
            l.add(hc.getUser());
        }
        return l;
    }

    // TODO return type? (python c'est cool pour le packing)
    public String addCompletedChallenge(long challengeId, long userId, String commentary, String picture){
        User user = userRepository.findById(userId).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        HasCompleted hc = new HasCompleted();
        hc.setChallenge(challenge);
        hc.setUser(user);
        hc.setCommentary(commentary);
        hc.setPicture(picture);
        user.getHasCompleted().add(hc);
        challenge.getHasCompleted().add(hc);
        return "User " + user.getUsername() + " has completed " + challenge.getName();
    }

}
