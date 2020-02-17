package hr.fer.ppp.ferbook.api.rest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
