package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.dao.UsersRepository;
import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateUserDTO;
import hr.fer.ppp.ferbook.api.rest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @PostMapping("")
    public ResponseEntity<User> register(@RequestBody CreateUserDTO userDTO) {
        User user = usersService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getEmail());

        usersService.sendConfirmationMail(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
