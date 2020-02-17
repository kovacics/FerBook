package hr.fer.ppp.ferbook.config.security;

import hr.fer.ppp.ferbook.api.dao.UsersRepository;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong username");
        }
        return new CustomUserDetails(user);
    }
}
