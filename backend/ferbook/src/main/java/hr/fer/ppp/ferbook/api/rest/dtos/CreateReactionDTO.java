package hr.fer.ppp.ferbook.api.rest.dtos;

import hr.fer.ppp.ferbook.api.model.ReactionEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class CreateReactionDTO {

    @Enumerated(EnumType.STRING)
    private ReactionEnum reaction;

    private Integer activityID;
}
