package coronhackathon.backend.controller;

import coronhackathon.backend.entity.User;
import coronhackathon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /* ---Users ----*/
    @GetMapping("/api/allUsers")
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/api/getUser")
    public Optional<User> getUser(@RequestParam long id){
        return userService.getUser(id);
    }

    @GetMapping("/api/getUserByName")
    public Optional<User> getUserByName(@RequestParam String username){
        return userService.getUserByName(username);
    }

    @PostMapping("/api/addUser")
    /**
     * test with
     * curl -X POST localhost:8080/api/addUser -H 'Content-type:application/json' -d '{"username": "John Doe"}'
     * security config modifications were needed to allow post requests. See dedicated file.
     */
    public void addUser(@RequestBody User user) {
        userService.insert(user);
    }

    //TODO age
    //TODO Badge
}