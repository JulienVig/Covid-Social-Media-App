package coronhackathon.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    @GetMapping("/")
    public String hello(){
        return "Vous êtes bien sur l'application Spring de Ben";
    }
}
