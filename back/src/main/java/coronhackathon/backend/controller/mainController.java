package coronhackathon.backend.controller;

import coronhackathon.backend.entity.Challenge;
import coronhackathon.backend.entity.Tag;
import coronhackathon.backend.entity.User;
import coronhackathon.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    // TODO remove this test method when not needed anymore
    @GetMapping(
            value = "/banana",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        String path = "src/main/resources/banana.jpg";
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * Get an image from path
     * @param path : should start with 'resources/'
     * @return the image data as byte arraygit reset
     * @use ip:8080/api/image?<path> where <path> was received from a previous query
     * @throws IOException
     */
    @GetMapping(
            value = "/api/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam String path) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/" + path));
    }

    /*
    TODO remove when unecessary
    use it as test method

     */
    @PostMapping(
            value = "/uploadImage",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody String uploadImageTest(@RequestBody byte[] imgData) throws IOException {
        String destinationPath = "src/main/resources/upload.jpg";

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgData));
        ImageIO.write(img, "jpg", new File(destinationPath));

        return destinationPath;
    }

    /**
     * Receive an image illustrating a completion, save it and set path in hasCompleted entity
     * @param imgData: the uploaded image as byte array. see https://www.tutorialspoint.com/How-to-convert-Image-to-Byte-Array-in-java
     * @param id: the id of hasCompleted entity received from a previous query
     * @return the path to find the image
     * @use ip:8080/api/uploadImage/hasCompletedID where <path> was received from a previous query
     * @throws IOException
     */
    @RequestMapping(
            value = "/api/uploadImage/{userId}-{challengeId}",
            method = RequestMethod.POST,
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody String uploadImage(
            @RequestBody byte[] imgData,
            @PathVariable("userId") long userId,
            @PathVariable("challengeId") long challengeId) throws IOException {
        String destinationPath = "resources/completedImage/hasCompleted_"
                + Long.toString(userId)
                + "_"
                + Long.toString(challengeId)+".jpg";

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgData));
        ImageIO.write(img, "jpg", new File("src/main/"+destinationPath) );
        completedService.setPath(userId, challengeId, destinationPath);
        return destinationPath;
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
     * Returns a list with all the challenges that are in a category
     * @param category a category of challenges
     * @return a list with all the challenge of a category
     */
    @RequestMapping(path = "/api/getChallengeByCategory/{category}", method = RequestMethod.GET)
    public List<Challenge> getChallengeByCategory(@PathVariable String category){
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
    @RequestMapping(path = "/api/getChallengeByName/{name}", method = RequestMethod.GET)
    public Optional<Challenge> getChallengeByName(@PathVariable String name) {
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
    @RequestMapping(path = "/api/numberOfChallengesOfCategory/{category}", method = RequestMethod.GET)
    public Long numberOfChallengesOfCategory(@PathVariable String category) {
        return challengeService.numberOfChallengesByCategory(category);
    }


    /**
     * Add a challenge given as argument to the database
     * @param challenge a challenge we want to add
     */
    @RequestMapping(path = "/api/addChallenge/{challenge}", method = RequestMethod.GET)
    public void addChallenge(@PathVariable Challenge challenge) {
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