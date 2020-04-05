package coronhackathon.backend.repository;

import coronhackathon.backend.entity.HasCompleted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedRepository extends JpaRepository<HasCompleted, Long> {
}
