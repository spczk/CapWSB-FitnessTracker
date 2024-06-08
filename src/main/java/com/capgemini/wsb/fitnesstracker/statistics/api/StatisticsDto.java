package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import jakarta.annotation.Nullable;


public record StatisticsDto(@Nullable Long Id, UserDto user, int totalTrainings,
               @Nullable double totalDistance, int totalCaloriesBurned) {

}
