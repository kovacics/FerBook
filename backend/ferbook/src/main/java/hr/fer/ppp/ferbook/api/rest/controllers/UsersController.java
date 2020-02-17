package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.api.rest.dtos.UpdateUserDTO;
import hr.fer.ppp.ferbook.api.rest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/start/{s}")
    public List<User> getAllUsersByUsernameStart(@PathVariable String s) {
        return usersService.getAllUsersByUsernameStart(s);
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable int id) {
        return usersService.getUserByID(id);
    }

    @PutMapping("/{id}")
    public void replaceUser(@RequestBody User user, @PathVariable int id) {
        usersService.replaceUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
    }

    @GetMapping("/confirmation/{token}")
    public ResponseEntity<?> confirmUser(@PathVariable String token) {
        boolean confirmed = usersService.confirmUser(token);
        if (!confirmed) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping("/{id}")
    public void updateUser(@RequestBody UpdateUserDTO user, @PathVariable int id) {
        usersService.updateUser(id, user);
    }
}
