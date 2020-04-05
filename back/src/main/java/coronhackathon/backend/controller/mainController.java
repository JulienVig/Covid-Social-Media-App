package coronhackathon.backend.controller;

import coronhackathon.backend.entity.Category;
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
    @Autowired
    private CategoryService categoryService;

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

    @RequestMapping(path = "/api/getUser/{userId}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(path = "/api/getUserByName/{username}", method = RequestMethod.GET)
    public Optional<User> getUserByName(@PathVariable String username) {
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

    @RequestMapping(path = "/api/getCompleted/{userId}", method = RequestMethod.GET)
    public List<Challenge> getCompletedChallenges(@PathVariable long userId){
        return completedService.getCompletedChallenges(userId);
    }


    /**
     * Retrieve all users that complete challenge Challenge
     * @param challengeId the id of Challenge
     * @return completers as a list
     */
    @RequestMapping(path = "/api/getCompleters/{challengeId}", method = RequestMethod.GET)
    public List<User> getCompletersOfChallenge(@PathVariable long challengeId){
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
     * @param challengeId the id of a challenge
     * @return the challenge with the specified id
     */
    @RequestMapping(path = "/api/getChallenge/{challengeId}", method = RequestMethod.GET)
    public Optional<Challenge> getChallenge(@PathVariable long challengeId) {
        return challengeService.getChallenge(challengeId);
    }

    /**
     * Returns the number of challenges of a given category
     * @param category the name of the category
     * @return the number of challenges in the category
     */
    @RequestMapping(path = "/api/numberOfChallengesOfCategory/{category}", method = RequestMethod.GET)
    public Long numberOfChallengesOfCategory(@PathVariable Category category) {
        return challengeService.numberOfChallengesByCategory(category);
    }

    /**
     * Returns an Optional that contains a challenge with a specified name if it exists
     * otherwise returns an empty Optional
     * @param name the name of a challenge
     * @return the challenge with the specified name
     */
    @RequestMapping(path = "/api/getChallengeByName/{name}", method = RequestMethod.GET)
    public Optional<Challenge> getChallengeByName(@PathVariable String name) {
        return challengeService.getChallengeByName(name);
    }

    /**
     * Returns a list with all the challenges that are in a category
     * @param category a category of challenges
     * @return a list with all the challenge of a category
     */
    @RequestMapping(path = "/api/getChallengeByCategory/{category}", method = RequestMethod.GET)
    public List<Challenge> getChallengeByCategory(@PathVariable Category category){
        return challengeService.getChallengeByCategory(category);
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
     * Add a challenge given as argument to the database
     * @param challenge a challenge we want to add
     */
    @RequestMapping(path = "/api/addChallenge/{challenge}", method = RequestMethod.GET)
    public void addChallenge(@PathVariable Challenge challenge) {
        challengeService.addChallenge(challenge);
    }


    /* ----Category---- */

    /**
     *
     * @param id of the category
     * @return the category corresponding to the id
     */
    @RequestMapping(path = "/api/getCategory/{id}", method = RequestMethod.GET)
    public Optional<Category> getCategory(@PathVariable long id) {
        return categoryService.getCategory(id);
    }

    /**
     *
     * @param name of the category
     * @return the category corresponding to the name
     */
    @RequestMapping(path = "/api/getCategoryByName/{name}", method = RequestMethod.GET)
    public Optional<Category> getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

    /**
     *  Adds a category to the database
     * @param category
     * @return
     */
    @PostMapping("/api/addCategory")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }


    /**
     * Returns a list with all the categories
     * @param
     * @return a list with all the categories
     */
    @GetMapping("/api/allCategories")
    public List<Category> allCategories(){
        return categoryService.allCategories();
    }

    /**
     * Returns the number of categories
     * @param
     * @return a long, the number of categories
     */
    @GetMapping("/api/numberOfCategories")
    public long numberOfCategories(){
        return categoryService.numberOfCategories();
    }



    /**
     * Returns all challenges completed by User in a certain category
     * @param userId Id of User
     * @param category category of the challenges completed by the user
     * @return completed challenges of a certain category as a list
     */
    @RequestMapping(path = "/api/getCompletedByCat/{userId}/{category}", method = RequestMethod.GET)
    public List<Challenge> getCompletedChallengesByCategory(@PathVariable long userId, @PathVariable Category category){
        return completedService.getCompletedChallengesByCategory(userId,category);
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
    @RequestMapping(path = "/api/getChallengesByTag/{tagId}", method = RequestMethod.GET)
    public List<Challenge> getChallengesByTag(@PathVariable long tagId){
        return isAService.getChallengesOfTag(tagId);
    }


}