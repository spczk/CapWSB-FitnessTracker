package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training createTraining(final Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    public Training updateTraining(final Training training) {
        log.info("Updating Training {}", training);
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training does not exist in DB.");
        }
        return trainingRepository.save(training);
    }

    public List<Training> getTrainingsByUser(final Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    public List<Training> getTrainingsByEndTime(final Date date) {
        return trainingRepository.findByEndTime(date);
    }

    public List<Training> getTrainingsByActivityType(final ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    public List<Training> getTrainingsWithDistanceGreaterThan(final double distance)
    {
        return trainingRepository.findByDistanceGreaterThan(distance);
    }

}
