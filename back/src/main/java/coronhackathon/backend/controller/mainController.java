package coronhackathon.backend.controller;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.service.ChallengeService;
import coronhackathon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class mainController {
    @Autowired
    private UserService userService;
    private ChallengeService challengeService;

    //TODO delete this test method when not needed anymore
    @GetMapping("/ping")
    public String ping(){ return "pong!"; }

    @PostMapping("/register")
    public Optional<User> showRegistrationForm(@RequestParam String username,
                                               @RequestParam String hashPwd,
                                               @RequestParam String hashPwd2) {
        return userService.register(username, hashPwd, hashPwd2);
    }

    //TODO delete this test method when not needed anymore
    @GetMapping("/api/ping")
    public String fancyPing(){
        return "authenticated pong!";
    }

    /* ---Users ----*/
    @GetMapping("/api/allUsers")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/getUser")
    public Optional<User> getUser(@RequestParam long id) {
        return userService.getUser(id);
    }

    @GetMapping("/api/getUserByName")
    public Optional<User> getUserByName(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/api/addUser")
    /**
     * test with
     * curl -X POST localhost:8080/api/addUser -H 'Content-type:application/json' -d '{"username": "John Doe"}'
     * security config modifications were needed to allow post requests. See dedicated file.
     */
    public void addUser(@RequestBody User user) { userService.insert(user);
    }

    //TODO age
    //TODO Badge

    /* ----Challenge---- */

    /**
     * Returns all the challenges stored in the database
     * curl -X GET localhost:8080/api/allChallenges
     *
     * @return a list that contains all the challenges stored in the database
     */
    @GetMapping("/api/allChallenges")
    public List<Challenge> allChallenges() {
        return challengeService.allChallenges();
    }

    /**
     * Returns an Optional that contains a challenge with a specified id if it exists
     * otherwise returns an empty Optional
     * @param id the id of a challenge
     * @return the challenge with the specified id
     */
    @GetMapping("/api/getChallenge")
    public Optional<Challenge> getChallenge(@RequestParam long id) {
        return challengeService.getChallenge(id);
    }

    /**
     * Returns a list with all the challenges that are in a category
     * @param category a category of challenges
     * @return a list with all the challenge of a category
     */
    @GetMapping("/api/getChallengeByCategory")
    public List<Challenge> getChallengeByCategory(@RequestParam String category){
        return challengeService.getChallengeByCategory(category);
    }

    @GetMapping("/api/allCategories")
    public List<String> allCategories(){
        return challengeService.allCategories();
    }


    /**
     * Returns an Optional that contains a challenge with a specified name if it exists
     * otherwise returns an empty Optional
     * @param name the name of a challenge
     * @return the challenge with the specified name
     */
    @GetMapping("/api/getChallengeByName")
    public Optional<Challenge> getChallengeByName(@RequestParam String name) {
        return challengeService.getChallengeByName(name);
    }

    /**
     * Add a challenge given as argument to the database
     * @param challenge a challenge we want to add
     */
    @PostMapping("/api/addChallenge")
    public void addChallenge(@RequestBody Challenge challenge) {
        challengeService.addChallenge(challenge);
    }
}