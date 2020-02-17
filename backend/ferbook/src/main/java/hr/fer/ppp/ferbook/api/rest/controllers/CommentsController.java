package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Comment;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateCommentDTO;
import hr.fer.ppp.ferbook.api.rest.services.CommentsService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("")
    public List<Comment> getAllComments() {
        return commentsService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentByID(@PathVariable int id) {
        return commentsService.getComment(id);
    }

    @PostMapping("")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentDTO commentDAO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        return new ResponseEntity<>(commentsService.createComment(userID, commentDAO.getActivityID(),
                commentDAO.getContent()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void replaceComment(@RequestBody Comment comment, @PathVariable int id) {
        commentsService.replaceComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) {
        commentsService.deleteComment(id);
    }
}
