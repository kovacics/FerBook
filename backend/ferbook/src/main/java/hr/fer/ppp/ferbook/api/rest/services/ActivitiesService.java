package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.ActivitiesRepository;
import hr.fer.ppp.ferbook.api.model.Activity;
import hr.fer.ppp.ferbook.api.model.Picture;
import hr.fer.ppp.ferbook.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivitiesService {

    @Autowired
    private ActivitiesRepository activitiesRepository;

    @Autowired
    private FriendshipsService friendshipsService;

    @Autowired
    private PicturesService picturesService;

    @Autowired
    private UsersService usersService;


    public List<Activity> getActivities() {
        return activitiesRepository.findAll();
    }

    public List<Activity> getActivitiesByUserID(int id) {
        Comparator<Activity> activityComparator = Comparator.comparing(Activity::getCreatedAt).reversed();
        return activitiesRepository.findAllByUserID(id).stream()
                .sorted(activityComparator)
                .collect(Collectors.toList());
    }

    public Activity getActivity(int id) {
        return activitiesRepository.findById(id).orElse(null);
    }

    public void deleteActivity(int id) {
        activitiesRepository.deleteById(id);
    }

    public void replaceActivity(int id, Activity activity) {
        activity.setID(id);
        activitiesRepository.save(activity);
    }

    public List<Activity> getFriendsActivitiesByUserID(int id) {
        List<User> friends = friendshipsService.getAllFriendsOfUserWithID(id);
        List<Activity> activities = new ArrayList<>();
        for (User friend : friends) {
            activities.addAll(activitiesRepository.findAllByUserID(friend.getID()));
        }
        Comparator<Activity> activityComparator = Comparator.comparing(Activity::getCreatedAt).reversed();
        return activities.stream()
                .sorted(activityComparator)
                .collect(Collectors.toList());
    }

    public Activity createActivity(int userID, int receiverID, String context, List<Integer> imageIDs) {

        Assert.hasText(context, "Activity content cannot be empty.");

        User user = usersService.getUserByID(userID);
        User reciever = usersService.getUserByID(receiverID);
        List<Picture> pictures = null;
        Activity activity = new Activity(user, context, reciever, pictures);

        if (imageIDs != null) {
            pictures = imageIDs.stream()
                    .map(imID -> picturesService.getPicture(imID))
                    .collect(Collectors.toList());

            pictures.forEach(p -> p.setUser(user));
            pictures.forEach(p -> p.setActivity(activity));
        }

        activity.setPictures(pictures);
        return activitiesRepository.save(activity);
    }

    public List<Activity> getActivitiesContaining(String s) {
        return activitiesRepository.findAllByContextContainingIgnoreCase(s);
    }
}
