package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByUsernameStartingWithIgnoreCase(String s);

    User findByConfirmationToken(String token);
}
