package coronhackathon.backend.repository;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedRepository extends JpaRepository<HasCompleted, Long> {
    public List<HasCompleted> findByUser(User user);
    public List<HasCompleted> findByChallenge(Challenge challenge);
}
