package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionsRepository extends JpaRepository<Reaction, Integer> {

    List<Reaction> findAllByActivityID(int id);
}
