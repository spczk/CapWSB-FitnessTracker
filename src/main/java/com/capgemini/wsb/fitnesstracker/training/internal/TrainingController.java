package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class UserController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }
    

    @PostMapping("/add")
    public Training addTraining(@RequestBody TrainingDto trainingDto) {

        Training training = trainingMapper.toEntity(trainingDto);

        return trainingService.createTraining(training);
    }

    @GetMapping("/user/{id}")
    public List<Training> getTrainingsByUser(@PathVariable Long id) {
        return trainingService.getTrainingsByUser(id);
    }

    @GetMapping("/end_time/{date}")
    public List<Training> getTrainingsByEndTime(@PathVariable Date date) {
        return trainingService.getTrainingsByEndTime(date);
    }

    @GetMapping("/activity_type/{activityTypeString}")
    public List<Training> getTrainingsByActivityType(@PathVariable String activityTypeString) {
        ActivityType activityType = ActivityType.valueOf(activityTypeString);
        return trainingService.getTrainingsByActivityType(activityType);
    }

    @PutMapping("/update_distance/{id}")
    public Training updateTrainingDistance(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        Training training = trainingService.getTraining(id)
                                          .orElseThrow(() -> new IllegalArgumentException("Training not found"));
        training.setDistance(trainingDto.distance());
        return trainingService.updateTraining(training);
    }

    @GetMapping("/distance_greater_than/{distance}")
    public List<Training> getTrainingsWithDistanceGreaterThan(@PathVariable Double distance) {
        return trainingService.getTrainingsWithDistanceGreaterThan(distance);
    }
}