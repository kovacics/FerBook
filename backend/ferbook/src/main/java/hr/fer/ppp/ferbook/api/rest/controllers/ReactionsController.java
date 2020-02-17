package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Reaction;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateReactionDTO;
import hr.fer.ppp.ferbook.api.rest.services.ReactionsService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reactions")
public class ReactionsController {

    @Autowired
    private ReactionsService reactionsService;

    @GetMapping("")
    public List<Reaction> getReactions() {
        return reactionsService.getReactions();
    }

    @GetMapping("/{id}")
    public Reaction getReaction(@PathVariable int id) {
        return reactionsService.getReaction(id);
    }

    @GetMapping("/activity/{id}")
    public List<Reaction> getReactionsByActivityID(@PathVariable int id) {
        return reactionsService.getReactionsByActivityID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable int id) {
        reactionsService.deleteReaction(id);
    }

    @PutMapping("/{id}")
    public void replaceReaction(@PathVariable int id, @RequestBody Reaction reaction) {
        reactionsService.replaceReaction(id, reaction);
    }

    @PostMapping("")
    public ResponseEntity<?> createReaction(@RequestBody CreateReactionDTO reactionDTO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        Reaction reaction = reactionsService.createReaction(userID, reactionDTO.getActivityID(), reactionDTO.getReaction());
        return new ResponseEntity<>(reaction, HttpStatus.CREATED);
    }
}
