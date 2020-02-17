package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumsRepository extends JpaRepository<Album, Integer> {

    List<Album> findAllByUserID(int id);
}
