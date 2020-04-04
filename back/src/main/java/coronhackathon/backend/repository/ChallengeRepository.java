package coronhackathon.backend.repository;

import coronhackathon.backend.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    public Optional<Challenge> findById(long id);
    public Optional<Challenge> findByName(String name);
}
