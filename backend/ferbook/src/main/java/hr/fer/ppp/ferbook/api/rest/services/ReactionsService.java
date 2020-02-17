package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.ReactionsRepository;
import hr.fer.ppp.ferbook.api.model.Activity;
import hr.fer.ppp.ferbook.api.model.Reaction;
import hr.fer.ppp.ferbook.api.model.ReactionEnum;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionsService {

    @Autowired
    private ReactionsRepository reactionsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActivitiesService activitiesService;

    public List<Reaction> getReactions() {
        return reactionsRepository.findAll();
    }

    public List<Reaction> getReactionsByActivityID(int id) {
        return reactionsRepository.findAllByActivityID(id);
    }

    public Reaction getReaction(int id) {
        return reactionsRepository.findById(id).orElse(null);
    }

    public void deleteReaction(int id) {
        reactionsRepository.deleteById(id);
    }

    public void replaceReaction(int id, Reaction reaction) {
        reaction.setId(id);
        reactionsRepository.save(reaction);
    }

    public Reaction createReaction(int userID, Integer activityID, ReactionEnum reactionEnum) {

        User user = usersService.getUserByID(userID);
        Activity activity = activitiesService.getActivity(activityID);

        Reaction reaction = new Reaction(reactionEnum, user, activity);
        return reactionsRepository.save(reaction);
    }
}
