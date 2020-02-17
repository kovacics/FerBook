package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.CommentsRepository;
import hr.fer.ppp.ferbook.api.model.Activity;
import hr.fer.ppp.ferbook.api.model.Comment;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActivitiesService activitiesService;


    public List<Comment> getAllComments() {
        return commentsRepository.findAll();
    }


    public Comment getComment(int id) {
        return commentsRepository.findById(id).orElse(null);
    }

    public void deleteComment(int id) {
        commentsRepository.deleteById(id);
    }

    public void replaceComment(int id, Comment comment) {
        comment.setID(id);
        commentsRepository.save(comment);
    }

    public Comment createComment(int userID, int activityID, String content) {

        Assert.hasText(content, "Content must have text");

        Comment comment = new Comment();

        User user = usersService.getUserByID(userID);
        Activity activity = activitiesService.getActivity(activityID);

        comment.setUser(user);
        comment.setActivity(activity);
        comment.setContent(content);

        commentsRepository.save(comment);

        return comment;
    }
}
