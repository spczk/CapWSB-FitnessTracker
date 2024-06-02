package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

public record TrainingDto(Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance,
        double averageSpeed) {

}
