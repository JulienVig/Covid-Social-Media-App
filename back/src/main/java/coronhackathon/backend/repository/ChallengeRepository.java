package coronhackathon.backend.repository;

import coronhackathon.backend.entity.Category;
import coronhackathon.backend.entity.Challenge;

import coronhackathon.backend.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    public Optional<Challenge> findById(long id);
    public Optional<Challenge> findByName(String name);
    public List<Challenge> findByCategory(Category category);
    public long count();
    public Long countByCategory(Category category);

}
