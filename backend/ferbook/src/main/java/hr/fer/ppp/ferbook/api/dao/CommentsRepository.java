package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {

}
