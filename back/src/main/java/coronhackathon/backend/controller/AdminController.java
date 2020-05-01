package coronhackathon.backend.controller;

import com.sun.jmx.remote.internal.ArrayQueue;
import coronhackathon.backend.DTO.CompletedDTO;
import coronhackathon.backend.entity.Category;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.repository.CompletedRepository;
import coronhackathon.backend.service.ChallengeService;
import coronhackathon.backend.service.CompletedService;
import coronhackathon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    CompletedRepository completedRepository;
    @Autowired
    CompletedService completedService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(path = "/admin/notApprovedCompleted", method = RequestMethod.GET)
    public List<CompletedDTO> getNotApprovedCompleted() {
        List<HasCompleted> lhc =  completedRepository.findByApprovedTrue();
        List<CompletedDTO> lc = new ArrayList<>();
        for(HasCompleted hc : lhc)
            lc.add(new CompletedDTO(hc));
        return lc;
    }

    @PostMapping("/admin/approveCompleted")
    public String approveCompleted(@RequestParam long userId, @RequestParam long challengeId){
        return completedService.approveCompleted(userService.getUser(userId), challengeService.getChallenge(challengeId));
    }

    @PostMapping("/admin/refuseCompleted")
    public String refuseCompleted(@RequestParam long userId, @RequestParam long challengeId){
        return completedService.refuseCompleted(userService.getUser(userId), challengeService.getChallenge(challengeId));
    }
}
