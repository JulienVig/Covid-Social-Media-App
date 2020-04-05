package coronhackathon.backend.repository;


import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.Tag;
import coronhackathon.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Optional<Tag> findById(long tagId);
    public Optional<Tag> findByName(String name);
}
