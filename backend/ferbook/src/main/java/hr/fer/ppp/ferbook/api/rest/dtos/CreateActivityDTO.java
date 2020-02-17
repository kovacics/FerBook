package hr.fer.ppp.ferbook.api.rest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateActivityDTO {
    private String context;
    private int receiverID;
    private List<Integer> imageIDs;
}