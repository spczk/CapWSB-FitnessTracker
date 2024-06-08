package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Query searching trainings by user id.
     *
     * @param userId id of the user to search
     * @return {@link List} containing found trainings
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                        .filter(training -> training.getUser().getId().equals(userId)).toList();
    }

    /**
     * Query searching trainings by end time.
     *
     * @param date end date of the training to search
     * @return {@link List} containing found trainings
     */
    default List<Training> findByEndTime(Date date) {
        return findAll().stream()
                        .filter(training -> training.getEndTime().after(date)).toList();
    }

    /**
     * Query searching trainings by activity type.
     *
     * @param activityType activity type of the training to search
     * @return {@link List} containing found trainings
     */
    default List<Training> findByActivityType(ActivityType activityType) {
        return findAll().stream()
                        .filter(training -> training.getActivityType().equals(activityType)).toList();
    }

    default List<Training> findByDistanceGreaterThan(double distance) {
        return findAll().stream()
                        .filter(training -> training.getDistance() > distance).toList();
    }


}
