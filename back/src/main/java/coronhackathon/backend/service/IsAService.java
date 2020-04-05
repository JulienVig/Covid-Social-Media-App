package coronhackathon.backend.service;

import coronhackathon.backend.entity.*;
import coronhackathon.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IsAService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private IsARepository isARepository;


    public List<Challenge> getChallengesOfTag(long tagId) {
        List<Challenge> l = new ArrayList<Challenge>();
        Optional<Tag> oc = tagRepository.findById(tagId);
        if(!oc.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tag with id : "+tagId+" not found");
        for(IsA hc : isARepository.findByTag(oc.get())){
            l.add(hc.getChallenge());
        }
        return l;
    }


}
