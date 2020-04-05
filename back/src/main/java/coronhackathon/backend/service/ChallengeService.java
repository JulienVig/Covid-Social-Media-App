package coronhackathon.backend.service;

import coronhackathon.backend.entity.Category;
import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.repository.CategoryRepository;
import coronhackathon.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    public void addChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getChallenge(long id) {
        return challengeRepository.findById(id);
    }

    public List<Challenge> getChallengeByCategory(long categoryId) {
        return challengeRepository.findByCategory(categoryId);
    }

    public List<Challenge> getChallengeByCategory(String name) {
        return challengeRepository.findByCategory(categoryService.getIdFromName(name));
    }
    
    public Optional<Challenge> getChallengeByName(String name) {
        return challengeRepository.findByName(name);
    }

    public long numberOfChallenges() {
        return challengeRepository.count();
    }

    public Long numberOfChallengesByCategory(Category category) {
        return challengeRepository.countByCategory(category);
    }



}
