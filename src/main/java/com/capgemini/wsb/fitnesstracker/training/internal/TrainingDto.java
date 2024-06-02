package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

record TrainingDto(Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance,
        double averageSpeed) {

}
