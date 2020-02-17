package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Album;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateAlbumDTO;
import hr.fer.ppp.ferbook.api.rest.services.AlbumsService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumsService albumsService;

    @GetMapping("")
    public List<Album> getAlbums() {
        return albumsService.getAlbums();
    }

    @GetMapping("/album/{id}")
    public List<Album> getAlbumsByUserID(@PathVariable int id) {
        return albumsService.getAlbumsByUserID(id);
    }

    @GetMapping("/{id}")
    public Album getALbum(@PathVariable int id) {
        return albumsService.getAlbum(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable int id) {
        albumsService.deleteAlbum(id);
    }

    @PutMapping("/{id}")
    public void replaceAlbum(@PathVariable int id, @RequestBody Album album) {
        albumsService.replaceAlbum(id, album);
    }

    @PostMapping("")
    public ResponseEntity<Album> createAlbum(@RequestBody CreateAlbumDTO albumDTO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        Album album = albumsService.createAlbum(userID, albumDTO.getAlbumName(), albumDTO.getDescription(),
                albumDTO.getImageIDs());
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }
}
