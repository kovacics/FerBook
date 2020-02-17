package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.PicturesRepository;
import hr.fer.ppp.ferbook.api.model.Activity;
import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicturesService {

    @Autowired
    private PicturesRepository picturesRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    PicturesService picturesService;

    @Autowired
    ActivitiesService activitiesService;

    public List<Picture> getAllPictures() {
        return picturesRepository.findAll();
    }

    public Picture getPicture(int id) {
        return picturesRepository.findById(id).orElse(null);
    }

    public void replacePicture(Picture picture, int id) {
        picture.setID(id);
        picturesRepository.save(picture);
    }

    public void deletePicture(int id) {
        picturesRepository.deleteById(id);
    }

    public List<Picture> getAllPicturesByUserID(int userID) {
        return picturesRepository.findAllByUserID(userID);
    }

    public List<Picture> getAllPicturesByAlbumID(int albumID) {
        return picturesRepository.findAllByAlbumID(albumID);
    }

    public Picture savePicture(int userID, int pictureID, int activityID, String description) {
        User user = usersService.getUserByID(userID);
        Picture picture = picturesService.getPicture(pictureID);
        Activity activity = activitiesService.getActivity(activityID);

        picture.setActivity(activity);
        picture.setUser(user);
        picture.setDescription(description);
        return picture;
    }

    public void savePicture(Picture picture) {
        picturesRepository.save(picture);
    }
}
