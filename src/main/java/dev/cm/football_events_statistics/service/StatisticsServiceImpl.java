package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.GetStatisticsMessageDto;
import dev.cm.football_events_statistics.exception.TeamNotFoundException;
import dev.cm.football_events_statistics.model.TeamStatistics;
import dev.cm.football_events_statistics.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class StatisticsServiceImpl implements MessageService<GetStatisticsMessageDto> {

    private final TeamRepository teamRepository;

    @Autowired
    StatisticsServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<String> process(GetStatisticsMessageDto message) {
        return message.getData().getTeams().stream().map(this::getTeamStatistics).collect(Collectors.toList());
    }

    private String getTeamStatistics(String teamName) {
        TeamStatistics teamStatistics = teamRepository
                .findTeamStatisticsByName(teamName)
                .orElseThrow(() -> new TeamNotFoundException(teamName));
        return teamStatistics.getStatistics();
    }
}
