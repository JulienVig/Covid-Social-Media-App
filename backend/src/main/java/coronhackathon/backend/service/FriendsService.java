package coronhackathon.backend.service;


import coronhackathon.backend.DTO.UserAndNbChallengeDTO;
import coronhackathon.backend.entity.Friends;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.repository.FriendsRepository;
import coronhackathon.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
public class FriendsService {
    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompletedService completedService;



    public List<User> getFriendsRequests(User user) {
        List<User> friends = new ArrayList<>();
        for(Friends f : friendsRepository.findByUser2(user)) {
            if (!f.getCompleted()) {
                friends.add(f.getUser1());
            }
        }
        return friends;
    }

    public boolean isFriend(long user1Id, long user2Id) {
        User user1 = userService.getUser(user1Id);
        User user2 = userService.getUser(user2Id);
        Optional<Friends> of = friendsRepository.findByUser1AndUser2(user1, user2);
        Optional<Friends> of2 = friendsRepository.findByUser1AndUser2(user2, user1);
        return (of.isPresent() && of.get().getCompleted())  ||
                (of2.isPresent() && of2.get().getCompleted());
    }

    public String friendRequest(User currentUser, String username) {
        User user = userService.getUserByUsername(username);
        if(user.equals(currentUser))
            return "You cannot be friends with yourself";

        Optional<Friends> of1 = friendsRepository.findByUser1AndUser2(currentUser, user);
        Optional<Friends> of2 = friendsRepository.findByUser1AndUser2(user, currentUser);
        if (of1.isPresent()) {  //if currentUser already asked user to be his/her friend
                                //and user has or has not already answered
            Friends f1 = of1.get();
            if (f1.getCompleted())
                return "" + currentUser.getUsername() + " and " + user.getUsername() + " are already friends";
            else
                return "user : " + currentUser.getUsername() + " has already asked " +
                        "" + user.getUsername() + " to be his/her friend";
        }else if (of2.isPresent()) {//if user already asked currentUser to be his/her friend
                                    //and currentUser has or has not already answered
            Friends f2 = of2.get();
            if (f2.getCompleted())
                return "" + currentUser.getUsername() + " and " + user.getUsername() + " are already friends";
            else{
                f2.setCompleted(true);
                friendsRepository.save(f2);
                return ""+currentUser.getUsername()+" and "+user.getUsername()+" are now friends";
            }
        }else { //if they never asked each other
            Friends friends = new Friends();
            friends.setUser1(currentUser);
            friends.setUser2(user);
            friends.setCompleted(false);
            friendsRepository.save(friends);
            return "" + currentUser.getUsername() + " asked " + user.getUsername() + " to be his/her friend";
        }
    }


    public String acceptFriendRequest(User currentUser, long userId) {
        User user = userService.getUser(userId);
        Optional<Friends> of = friendsRepository.findByUser1AndUser2(user, currentUser);
        if (!of.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "!!! Should not happen !!! Report this error !!! but it is because user : " + user.getUsername() + " has not asked " +
                    "" + currentUser.getUsername() + " to be his/her friend.");
        }
        Friends f = of.get();
        if(f.getCompleted())
            return ""+currentUser.getUsername()+" is already friend with "+user.getUsername();
        else {
            f.setCompleted(true);
            friendsRepository.save(f);
            return "" + currentUser.getUsername() + " and " + user.getUsername() + " are now friends";
        }
    }

    public String refuseFriendRequest(User currentUser, long userId) {
        User user = userService.getUser(userId);
        Optional<Friends> of = friendsRepository.findByUser1AndUser2(user, currentUser);
        if (!of.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "!!! Should not happen !!! Report this error !!! but it is because user : " + user.getUsername() + " has not asked " +
                    "" + currentUser.getUsername() + " to be his/her friend.");
        }else{
            friendsRepository.delete(of.get());
            return "Request from " + user.getUsername() + " to " + currentUser.getUsername() + " deleted";
        }
    }


    public List<UserAndNbChallengeDTO> getFriendsOrderByCompletedChallenges(User user) {
        List<UserAndNbChallengeDTO> friends = new ArrayList<>();
        for(Friends f : friendsRepository.findByUser1(user)) {
            if (f.getCompleted()) {
                User u = f.getUser2();
                friends.add(new UserAndNbChallengeDTO(u.getId(), u.getUsername(),
                        completedService.getNumberOfCompletedChallenges(u.getId())));
            }
        }
        for(Friends f : friendsRepository.findByUser2(user)) {
            if (f.getCompleted()) {
                User u = f.getUser1();
                friends.add(new UserAndNbChallengeDTO(u.getId(), u.getUsername(),
                        completedService.getNumberOfCompletedChallenges(u.getId())));
            }
        }
        friends.sort((x,y) -> x.getNbChall() == y.getNbChall()? 0 :
                (x.getNbChall() > y.getNbChall() ? 1 : 0 ));
        return friends;
    }
}
