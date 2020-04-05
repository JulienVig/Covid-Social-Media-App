package coronhackathon.backend.repository;

import coronhackathon.backend.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    public Optional<Challenge> findById(long id);
    public Optional<Challenge> findByName(String name);
    public List<Challenge> findByCategory(String category);
    public List<String> findDistinctByNameNotIn(List<Challenge> challenges);
    public long count();
    public Long countByCategory(String category);
    //public List <Challenge> findByIsA_tag_id(long tag_id);
}
