package hr.fer.ppp.ferbook.api.rest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateAlbumDTO {
    private String description;
    private String albumName;
    private List<Integer> imageIDs;
}
