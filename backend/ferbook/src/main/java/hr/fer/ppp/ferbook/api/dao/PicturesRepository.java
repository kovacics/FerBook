package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicturesRepository extends JpaRepository<Picture, Integer> {

    List<Picture> findAllByUserID(int userID);

    List<Picture> findAllByAlbumID(int albumID);
}
