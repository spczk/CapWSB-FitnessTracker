package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StatisticsMapper {

    private final UserProvider userProvider;

    StatisticsDto toDto(Statistics statictics) {

        return new StatisticsDto(statictics.getId(),
                               new UserDto(statictics.getUser().getId(),
                                           statictics.getUser().getFirstName(),
                                           statictics.getUser().getLastName(),
                                           statictics.getUser().getBirthdate(),
                                           statictics.getUser().getEmail()),
                                statictics.getTotalTrainings(),
                                statictics.getTotalDistance(),
                                statictics.getTotalCaloriesBurned()
                               );
    }

    Statistics toEntity(StatisticsDto statisticsDto) {
        User user = userProvider.getUser(statisticsDto.user().id())
                                 .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new Statistics(user,
                            statisticsDto.totalTrainings(),
                            statisticsDto.totalDistance(),
                            statisticsDto.totalCaloriesBurned());
    }

}