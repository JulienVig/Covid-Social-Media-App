package coronhackathon.backend.repository;


import coronhackathon.backend.entity.Tag;
import coronhackathon.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Optional<Tag> getTagById(long id);
    public Optional<Tag> getTagByName(String name);
}
