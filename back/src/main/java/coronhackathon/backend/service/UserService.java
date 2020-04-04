package coronhackathon.backend.service;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.TODO;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.entity.UserDto;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }


    public void insert(User user) {
        userRepository.save(user);
    }


    public Optional<User> login(String username, String hash) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (
                    user.get().getPwdHash()
                            .equals(passwordEncoder.encode(hash))
            ) return user;
            else return Optional.empty();
        } else return Optional.empty();
        // should be unique anyway because username is unique
        // TODO add internal behavior, at least register that this user is logged in
    }

    /**
     * Username must be unique and that the two hashes match
     *
     * @param username
     * @param hashPwd
     * @param hashPwd2
     * @return
     */
    public Optional<User> register(String username, String hashPwd, String hashPwd2) {
        Optional<User> optUser;
        if (hashPwd.equals(hashPwd2)) {
            if (!userRepository.findByUsername(username).isPresent()) {
                User user = new User();
                user.setUsername(username);
                user.setPwdHash(passwordEncoder.encode(hashPwd));
                insert(user);
                return Optional.of(user);
            } else {
                //TODO throw exception because username already exists
                //for the moment return empty optional
                return Optional.empty();
            }
        } else {
            //TODO throw exception because pwds are not the same
            //for the moment return empty optional
            return Optional.empty();
        }
    }

    @Transactional
    public User registerNewUserAccount(UserDto accountDto)
            throws UsernameExistsException {

        if (!userRepository.findByUsername(accountDto.getUsername()).isPresent()) {
            throw new UsernameExistsException(
                    "There is an account with that email address:" + accountDto.getUsername()
            );
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPwdHash(accountDto.getPassword());
        //user.setRoles(Arrays.asList("ROLE_USER"));
        return userRepository.save(user);
    }

}
