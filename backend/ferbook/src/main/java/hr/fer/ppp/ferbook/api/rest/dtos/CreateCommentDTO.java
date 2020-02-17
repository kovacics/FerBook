package hr.fer.ppp.ferbook.api.rest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
    private String content;
    private int activityID;
}