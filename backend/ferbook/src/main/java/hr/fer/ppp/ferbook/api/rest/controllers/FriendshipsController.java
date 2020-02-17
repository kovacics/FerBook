package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Friendship;
import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateFriendshipDTO;
import hr.fer.ppp.ferbook.api.rest.services.FriendshipsService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendships")
public class FriendshipsController {

    @Autowired
    private FriendshipsService friendshipsService;

    @GetMapping("")
    public List<Friendship> getAllFriendships() {
        return friendshipsService.getAllFriendships();
    }

    @GetMapping("/{id}")
    public Friendship getFriendshipByID(@PathVariable int id) {
        return friendshipsService.getFriendshipByID(id);
    }

    @GetMapping("/{userID}/requests")
    public List<Friendship> getAllUnconfirmedFriendshipsByFriendID(@PathVariable int userID) {
        return friendshipsService.getAllUnconfirmedFriendshipsByFriendID(userID);
    }

    @GetMapping("/{userID}/friends")
    public List<User> getUserFriends(@PathVariable int userID) {
        return friendshipsService.getAllFriendsOfUserWithID(userID);
    }

    @PostMapping("")
    public ResponseEntity<Friendship> createFriendship(@RequestBody CreateFriendshipDTO friendshipDTO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        return new ResponseEntity<>(friendshipsService.createFriendship(userID,
                friendshipDTO.getFriendId()), HttpStatus.CREATED);
    }

    @GetMapping("/{friendshipID}/confirm")
    public void confirmFriendship(@PathVariable int friendshipID) {
        friendshipsService.confirmFriendship(friendshipID);
    }

    @GetMapping("/{friendshipID}/reject")
    public void rejectFriendship(@PathVariable int friendshipID) {
        friendshipsService.rejectFriendship(friendshipID);
    }

    @PutMapping("/{id}")
    public void replaceFriendship(@RequestBody Friendship friendship, @PathVariable int id) {
        friendshipsService.replaceFriendship(id, friendship);
    }

    @DeleteMapping("/{id}")
    public void deleteFriendship(@PathVariable int id) {
        friendshipsService.deleteFriendship(id);
    }
}
