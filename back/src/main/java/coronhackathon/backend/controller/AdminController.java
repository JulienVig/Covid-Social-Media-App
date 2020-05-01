package coronhackathon.backend.controller;

import com.sun.jmx.remote.internal.ArrayQueue;
import coronhackathon.backend.DTO.CompletedDTO;
import coronhackathon.backend.entity.Category;
import coronhackathon.backend.entity.HasCompleted;
import coronhackathon.backend.repository.CompletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    CompletedRepository completedRepository;
    @RequestMapping(path = "/admin/notApprovedCompleted", method = RequestMethod.GET)
    public List<CompletedDTO> getNotApprovedCompleted() {
        List<HasCompleted> lhc =  completedRepository.findByApprovedTrue();
        List<CompletedDTO> lc = new ArrayList<>();
        for(HasCompleted hc : lhc)
            lc.add(new CompletedDTO(hc));
        return lc;
    }
}
