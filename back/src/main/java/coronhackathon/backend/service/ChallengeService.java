package coronhackathon.backend.service;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;


    public void addChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getChallenge(long id) {
        return challengeRepository.findById(id);
    }

    public List<Challenge> getChallengeByCategory(String category) {
        return challengeRepository.findByCategory(category);
    }
    
    public Optional<Challenge> getChallengeByName(String name) {
        return challengeRepository.findByName(name);
    }

    public List<String> allCategories() {
        return challengeRepository.findDistinctByNameNotIn(getAllChallenges());
    }

    public long numberOfChallenges() {
        return challengeRepository.count();
    }

    public Long numberOfChallengesByCategory(String category) {
        return challengeRepository.countByCategory(category);
    }

    public List<Challenge> getNineChallenges() {
        List<Challenge> all = getAllChallenges();
        Collections.shuffle(all);
        return all.subList(0,Math.min(9,all.size()));
    }
}
