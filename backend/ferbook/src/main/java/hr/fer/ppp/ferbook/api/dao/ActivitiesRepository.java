package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivitiesRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findAllByUserID(int id);

    List<Activity> findAllByContextContainingIgnoreCase(String s);
}
