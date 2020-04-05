package coronhackathon.backend.service;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.repository.ChallengeRepository;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompletedService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;


    public List<Challenge> getCompletedChallenges(long userId) {
        return new ArrayList<Challenge>(userRepository.findById(userId).get().getHas_completed());
    }

    public List<User> getCompletersOfChallenge(long challengeId) {
        return new ArrayList<User>(challengeRepository.findById(challengeId).get().getHas_completed());
    }

    // TODO return type? (python c'est cool pour le packing)
    public String addCompletedChallenge(long challengeId, long userId){
        User user = userRepository.findById(userId).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        user.getHas_completed().add(challenge);
        challenge.getHas_completed().add(user);

        return "User " + user.getUsername() + " has completed " + challenge.getName();
    }

}
