package hr.fer.ppp.ferbook.api.rest.services;

import com.google.common.hash.Hashing;
import hr.fer.ppp.ferbook.api.dao.UsersRepository;
import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.api.rest.dtos.UpdateUserDTO;
import hr.fer.ppp.ferbook.config.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    EmailsService emailsService;

    @Autowired
    PicturesService picturesService;


    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public List<User> getAllUsersByUsernameStart(String start) {
        return usersRepository.findAllByUsernameStartingWithIgnoreCase(start);
    }

    public User getUserByID(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public void replaceUser(int id, User user) {
        user.setID(id);
        usersRepository.save(user);
    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    public User createUser(String username, String password, String firstName, String lastName, String email) {

        Assert.hasText(username, "Username must have text");
        Assert.hasText(password, "Password must have text");
        Assert.hasText(firstName, "Firstname must have text");
        Assert.hasText(lastName, "Lastname must have text");
        Assert.hasText(email, "Email must have text");

        if (usersRepository.findByUsername(username) != null) {
            throw new BadRequestException("Username already used.");
        }

        if (usersRepository.findByEmail(email) != null) {
            throw new BadRequestException("Email already used.");
        }

        String toHash = username + "ferbook";
        String hashed = Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();

        User user = new User(username, password, firstName, lastName, email, hashed);
        return usersRepository.save(user);
    }

    public void sendConfirmationMail(User user) {
        emailsService.sendMail(
                "noreply.ferbook@gmail.com",
                user.getEmail(),
                "Account confirmation",
                "To confirm your account click the link below.\n\nhttp://ferbook-app.herokuapp.com/confirmation/" +
                        user.getConfirmationToken());
    }

    public boolean confirmUser(String token) {
        User user = usersRepository.findByConfirmationToken(token);
        if (user != null) {
            user.setConfirmedAt(new Timestamp(System.currentTimeMillis()));
            usersRepository.save(user);
            return true;
        }
        return false;
    }

    public void updateUser(int id, UpdateUserDTO userDTO) {
        User user = usersRepository.findById(id).orElse(null);

        if (user != null) {

            if (userDTO.getPassword() != null) {
                user.setPassword(userDTO.getPassword());
            }

            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }

            if (userDTO.getDateOfBirth() != null) {
                user.setDateOfBirth(userDTO.getDateOfBirth());
            }

            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }

            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }

            if (userDTO.getPictureId() != 0) {
                Picture picture = picturesService.getPicture(userDTO.getPictureId());
                if (picture != null) {
                    user.setPictureUrl(picture.getPictureUrl());
                }
            }

            if (userDTO.getUsername() != null) {
                user.setUsername(userDTO.getUsername());
            }

            usersRepository.save(user);
        }
    }
}
