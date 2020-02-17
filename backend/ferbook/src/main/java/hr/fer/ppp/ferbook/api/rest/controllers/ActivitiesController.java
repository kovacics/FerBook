package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Activity;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateActivityDTO;
import hr.fer.ppp.ferbook.api.rest.services.ActivitiesService;
import hr.fer.ppp.ferbook.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    @GetMapping("")
    public List<Activity> getActivities() {
        return activitiesService.getActivities();
    }

    @GetMapping("/user/{id}")
    public List<Activity> getActivitiesByUserID(@PathVariable int id) {
        return activitiesService.getActivitiesByUserID(id);
    }

    @GetMapping("/user/{id}/friends")
    public List<Activity> getFriendsActivitiesByUserID(@PathVariable int id) {
        return activitiesService.getFriendsActivitiesByUserID(id);
    }

    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable int id) {
        return activitiesService.getActivity(id);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable int id) {
        activitiesService.deleteActivity(id);
    }

    @PutMapping("/{id}")
    public void replaceActivity(@PathVariable int id, @RequestBody Activity activity) {
        activitiesService.replaceActivity(id, activity);
    }

    @PostMapping("")
    public ResponseEntity<Activity> createActivity(@RequestBody CreateActivityDTO activityDAO, Authentication authentication) {
        int userID = ((CustomUserDetails) authentication.getPrincipal()).getID();

        Activity activity = activitiesService.createActivity(userID, activityDAO.getReceiverID(),
                activityDAO.getContext(), activityDAO.getImageIDs());

        return new ResponseEntity<>(activity, HttpStatus.CREATED);
    }

    @GetMapping("/search/{s}")
    public List<Activity> getActivitiesContaining(@PathVariable String s) {
        return activitiesService.getActivitiesContaining(s);
    }
}
