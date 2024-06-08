package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class StatisticsServiceImpl implements StatisticsService, StatisticsProvider {

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics createStatistics(final Statistics statistics) {
        log.info("Creating Statistics {}", statistics);
        if (statistics.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return statisticsRepository.save(statistics);
    }

    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatistics'");
    }

}