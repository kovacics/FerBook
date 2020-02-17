package hr.fer.ppp.ferbook.api.rest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePictureDTO {

    private int pictureID;
    private int activityID;
    private String description;
}
