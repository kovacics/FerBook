package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.api.rest.dtos.CreatePictureDTO;
import hr.fer.ppp.ferbook.api.rest.services.PicturesService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pictures")
public class PicturesController {

    @Autowired
    private PicturesService picturesService;

    @GetMapping("")
    public List<Picture> getAllPictures() {
        return picturesService.getAllPictures();
    }

    @GetMapping("/{id}")
    public Picture getPicture(@PathVariable int id) {
        return picturesService.getPicture(id);
    }

    @PostMapping("")
    public ResponseEntity<Picture> createPicture(@RequestBody CreatePictureDTO pictureDTO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        Picture picture = picturesService.savePicture(userID, pictureDTO.getPictureID(),
                pictureDTO.getActivityID(), pictureDTO.getDescription());
        return new ResponseEntity<>(picture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void replacePicture(@RequestBody Picture picture, @PathVariable int id) {
        picturesService.replacePicture(picture, id);
    }

    @DeleteMapping("/{id}")
    public void deletePicture(@PathVariable int id) {
        picturesService.deletePicture(id);
    }

    @GetMapping("/user/{userID}")
    public List<Picture> getAllPicturesByUserID(@PathVariable int userID) {
        return picturesService.getAllPicturesByUserID(userID);
    }

    @GetMapping("/album/{albumID}")
    public List<Picture> getAllPicturesByAlbumID(@PathVariable int albumID) {
        return picturesService.getAllPicturesByAlbumID(albumID);
    }
}
