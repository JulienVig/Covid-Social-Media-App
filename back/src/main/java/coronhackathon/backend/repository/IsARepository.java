package coronhackathon.backend.repository;

import coronhackathon.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IsARepository extends JpaRepository<IsA, Long> {
    public List<IsA> findByChallenge(Challenge challenge);
    public List<IsA> findByTag(Tag tag);
}
