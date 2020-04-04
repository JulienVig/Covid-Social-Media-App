package coronhackathon.backend.controller;

import coronhackathon.backend.entity.User;
import coronhackathon.backend.entity.UserDto;
import coronhackathon.backend.service.UserService;
import coronhackathon.backend.service.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class mainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello(@RequestParam(defaultValue = "Moi") String name){ //Ajouter ?name=Votreprenom à la fin de l'URL
        return "Vous êtes bien sur l'application Spring de "+name;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UsernameExistsException e) {
            return null;
        }
        return registered;
    }

    @PostMapping("/user/registration")
    public User registerUserAccount(
            @ModelAttribute("user")  UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) throws Exception {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        else {
            System.out.println("errors!");
        }

        return registered;
    }

    @GetMapping("/api/allUsers")
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong!";
    }

    @GetMapping("/api/ping")
    public String fancyping(){
        return "fancypong!";
    }

    @PostMapping("/api/addUser")
    /**
     * test with
     * curl -X POST localhost:8080/api/addUser -H 'Content-type:application/json' -d '{"username": "John Doe"}'
     *
     * security config modifications were needed to allow post requests. See dedicated file.
     */
    public void addUser(@RequestBody User user) {
        userService.insert(user);
    }

   /* @PostMapping("/api/user/login")
    /**
     * curl -X POST localhost:8080/api/user/login -F 'username=MatMat' -F 'hash=1234'
     */
   /* public Optional<User> login(@RequestParam String username, @RequestParam long hash){

        return userService.login(username, hash);
            // renvoyer user

    }*/

   @PostMapping("/register")
   public Optional<User> showRegistrationForm(@RequestParam String username,
                                      @RequestParam String hashPwd,
                                      @RequestParam String hashPwd2) {

        return userService.register(username, hashPwd, hashPwd2);
   }

}