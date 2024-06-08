package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
class StatisticsController {

    private final StatisticsServiceImpl statisticsService;

    private final StatisticsMapper statisticsMapper;

    @PostMapping("/add")
    public Statistics addStatistics(@RequestBody StatisticsDto statisticsDto) {

        Statistics statistics = statisticsMapper.toEntity(statisticsDto);

        return statisticsService.createStatistics(statistics);
    }

}