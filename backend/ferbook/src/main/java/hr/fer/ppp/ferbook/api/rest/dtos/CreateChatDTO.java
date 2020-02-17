package hr.fer.ppp.ferbook.api.rest.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatDTO {
    private String name;
    private List<Integer> userIds;
}
