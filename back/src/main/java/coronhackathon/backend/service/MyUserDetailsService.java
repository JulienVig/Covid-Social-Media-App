package coronhackathon.backend.service;

import coronhackathon.backend.entity.User;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<User> o_user = userRepository.findByUsername(username);
        if (!o_user.isPresent()) {
            throw new UsernameNotFoundException(
                    "No user found with username: " + username);
        }
        User user = o_user.get();

        /*boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        */
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("USER"));

        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPwdHash(),
                        //Long.toString(user.getPwdHash()), enabled, accountNonExpired,
                        //credentialsNonExpired, accountNonLocked,
                       auth
                );
    }
    /*
    NOTE this is part of copypaste from baeldung. It is not necessary for our simple role system.

    */
    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        /*
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
         */
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("USER"));
        return auth;
    }

}