package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TrainingMapper {

    private final UserProvider userProvider;

    TrainingDto toDto(Training training) {

        return new TrainingDto(training.getId(),
                               new UserDto(training.getUser().getId(),
                                           training.getUser().getFirstName(),
                                           training.getUser().getLastName(),
                                           training.getUser().getBirthdate(),
                                           training.getUser().getEmail()),
                               training.getStartTime(),
                               training.getEndTime(),
                               training.getActivityType(),
                               training.getDistance(),
                               training.getAverageSpeed());
    }

    Training toEntity(TrainingDto trainingDto) {
        User user = userProvider.getUser(trainingDto.user().id())
                                 .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new Training(user,
                            trainingDto.startTime(),
                            trainingDto.endTime(),
                            trainingDto.activityType(),
                            trainingDto.distance(),
                            trainingDto.averageSpeed());
    }

}