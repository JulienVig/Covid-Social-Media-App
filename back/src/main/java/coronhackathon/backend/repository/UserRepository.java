package coronhackathon.backend.repository;


import coronhackathon.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> getUserById(long id);
    public List<User> getUserByUsername(String username);
}
