package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.AlbumsRepository;
import hr.fer.ppp.ferbook.api.model.Album;
import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumsService {

    @Autowired
    private AlbumsRepository albumsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PicturesService picturesService;


    public List<Album> getAlbums() {
        return albumsRepository.findAll();
    }

    public List<Album> getAlbumsByUserID(int id) {
        return albumsRepository.findAllByUserID(id);
    }

    public Album getAlbum(int id) {
        return albumsRepository.findById(id).orElse(null);
    }

    public void deleteAlbum(int id) {
        albumsRepository.deleteById(id);
    }

    public void replaceAlbum(int id, Album album) {
        album.setID(id);
        albumsRepository.save(album);
    }

    public Album createAlbum(int userID, String albumName, String description, List<Integer> imageIDs) {
        User user = usersService.getUserByID(userID);
        Album album = new Album(user, description, albumName, null);

        if (imageIDs != null) {
            addImagesByIDsToAlbum(imageIDs, user, album);
        }

        return albumsRepository.save(album);
    }

    public List<Picture> addImagesToAlbum(int userID, int albumId, List<Integer> imageIDs) {
        User user = usersService.getUserByID(userID);
        Album album = albumsRepository.getOne(albumId);

        if (imageIDs != null) {
            return addImagesByIDsToAlbum(imageIDs, user, album);
        }
        return null;
    }

    private List<Picture> addImagesByIDsToAlbum(List<Integer> imageIDs, User user, Album album) {
        List<Picture> pictures;
        pictures = imageIDs.stream()
                .map(imID -> picturesService.getPicture(imID))
                .collect(Collectors.toList());

        pictures.forEach(p -> p.setUser(user));
        pictures.forEach(p -> p.setAlbum(album));
        album.setPictures(pictures);
        albumsRepository.save(album);
        return pictures;
    }
}
