package dev.cm.football_events_statistics.service.impl;

import dev.cm.football_events_statistics.dto.GetStatisticsMessageDto;
import dev.cm.football_events_statistics.model.TeamStatistics;
import dev.cm.football_events_statistics.repository.TeamRepository;
import dev.cm.football_events_statistics.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements MessageService<GetStatisticsMessageDto> {

    private final TeamRepository teamRepository;

    @Autowired
    public StatisticsServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<String> process(GetStatisticsMessageDto message) {
        return message.getData().getTeams().stream().map(this::getTeamStatistics).collect(Collectors.toList());
    }

    private String getTeamStatistics(String teamName) {
        return teamRepository.getTeamStatisticsByName(teamName).orElse(new TeamStatistics(teamName)).getStatistics();
    }
}
