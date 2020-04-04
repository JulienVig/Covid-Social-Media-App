package coronhackathon.backend.repository;

import coronhackathon.backend.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    public Challenge getChallengeById(long id);
    public Challenge getChallengeByName(String name);
}
