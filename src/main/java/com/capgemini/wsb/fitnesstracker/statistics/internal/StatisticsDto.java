package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import jakarta.annotation.Nullable;


record StatisticsDto(@Nullable Long Id, UserDto user, int totalTrainings,
               @Nullable double totalDistance, int totalCaloriesBurned) {

}
