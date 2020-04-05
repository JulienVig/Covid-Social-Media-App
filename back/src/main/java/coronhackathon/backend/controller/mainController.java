package coronhackathon.backend.controller;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.Tag;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class mainController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private CompletedService completedService;
    @Autowired
    private TagService tagService;
    @Autowired
    private IsAService isAService;

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

    /* ----Completed---- */

    /**
     * Marks user and challenge as completed.
     * @param userId the completer's Id
     * @param challengeId the completed challenge's Id
     * @param commentary a commentary on the realisation of the challenge
     * @param picture a picture on the realisation of the challenge
     * @return a verification message
     */
    @PostMapping("/api/completeChallenge")
    public String completeChallenge(@RequestParam long userId, @RequestParam long challengeId,
                                    @RequestParam String commentary, @RequestParam String picture){
        return completedService.addCompletedChallenge(userId, challengeId, commentary, picture);
    }

    /**
     * Returns all challenges completed by User
     * @param userId Id of User
     * @return completed challenges as a list
     */
    @GetMapping("/api/getCompleted")
    public List<Challenge> getCompletedChallenges(@RequestParam long userId){
        return completedService.getCompletedChallenges(userId);
    }

    /**
     * Returns all challenges completed by User in a certain category
     * @param userId Id of User
     * @param category category of the challenges completed by the user
     * @return completed challenges of a certain category as a list
     */
    @RequestMapping(path = "/api/getCompletedByCat/{userId}/{category}", method = RequestMethod.GET)
    public List<Challenge> getCompletedChallengesByCategory(@PathVariable long userId, @PathVariable String category){
        return completedService.getCompletedChallengesByCategory(userId,category);
    }

    /**
     * Retrieve all users that complete challenge Challenge
     * @param challengeId the id of Challenge
     * @return completers as a list
     */
    @GetMapping("/api/getCompleters")
    public List<User> getCompletersOfChallenge(@RequestParam long challengeId){
        return completedService.getCompletersOfChallenge(challengeId);
    }


    /* ----Challenge---- */

    /**
     * Returns all the challenges stored in the database
     * curl -X GET localhost:8080/api/allChallenges
     *
     * @return a list that contains all the challenges stored in the database
     */
    @GetMapping("/api/allChallenges")
    public List<Challenge> allChallenges() {
        return challengeService.getAllChallenges();
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

    /**
     * Returns a list with all the categories
     * @param
     * @return a list with all the categories
     */
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
     * Returns the number of challenges
     * @return the number of challenges
     */
    @GetMapping("/api/numberOfChallenges")
    public long numberOfChallenges() {
        return challengeService.numberOfChallenges();
    }

    /**
     * Returns the number of challenges of a given category
     * @param category the name of the category
     * @return the number of challenges in the category
     */
    @GetMapping("/api/numberOfChallengesOfCategory")
    public Long numberOfChallengesOfCategory(@RequestParam String category) {
        return challengeService.numberOfChallengesByCategory(category);
    }


    /**
     * Add a challenge given as argument to the database
     * @param challenge a challenge we want to add
     */
    @PostMapping("/api/addChallenge")
    public void addChallenge(@RequestBody Challenge challenge) {
        challengeService.addChallenge(challenge);
    }


    /* ----Tag---- */
    /**
     * Add a tag given as argument to the database
     * @param tag a tag we want to add
     */
    @PostMapping("/api/addTag")
    public void addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
    }

    /**
     * Returns a list with all the tags
     * @param
     * @return a list with all the tags
     */
    @GetMapping("/api/allTags")
    public List<Tag> allTags(){
        return tagService.allTags();
    }

    /* ---- IsA ---- */

    /**
     * Retrieve all Challenges that have tht tag Tag
     * @param tagId the id of the tag
     * @return challenges as a list
     */
    @GetMapping("/api/getChallengesByTag")
    public List<Challenge> getChallengesByTag(@RequestParam long tagId){
        return isAService.getChallengesOfTag(tagId);
    }


}