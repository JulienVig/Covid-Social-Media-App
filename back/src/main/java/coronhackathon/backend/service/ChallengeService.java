package coronhackathon.backend.service;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.repository.ChallengeRepository;
import coronhackathon.backend.repository.CompletedRepository;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompletedService completedService;


    public void addChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getChallenge(long id) {
        return checkChallengeExists(challengeRepository.findById(id), "id", "" + id);
    }

    public Challenge getChallengeByName(String name) {
        return checkChallengeExists(challengeRepository.findByName(name), "name", "" + name);
    }

    public List<Challenge> getChallengeByCategory(long categoryId) {
        return challengeRepository.findByCategoryId(categoryId);
    }

    public long numberOfChallenges() {
        return challengeRepository.count();
    }

    public Long numberOfChallengesByCategory(long category) {
        return challengeRepository.countByCategoryId(category);
    }

    public List<Boolean> getChallengeBool(String username, long categoryId) {
        List<Boolean> l = new ArrayList<>();
        User u = userService.getUserByUsername(username);
        List<Challenge> completed = completedService.getCompletedChallengesByCategory(
                userService.getUserByUsername(username), categoryId);
        List<Challenge> cs = challengeRepository.findByCategoryId(categoryId);
        // creates the lists of booleans corresponding to the given list of challenges cs
        for (Challenge c : cs) {
            l.add(completed.contains(c));
        }
        return l;
    }

    private Challenge checkChallengeExists(Optional<Challenge> oc, String name, String value) {
        if (!oc.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "challenge with " + name + " : " + value + " not found");
        }
        return oc.get();
    }
}
