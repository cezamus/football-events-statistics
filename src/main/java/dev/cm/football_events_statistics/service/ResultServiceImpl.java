package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.ResultMessageDto;
import dev.cm.football_events_statistics.model.LastResults;
import dev.cm.football_events_statistics.model.TeamStatistics;
import dev.cm.football_events_statistics.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class ResultServiceImpl implements MessageService<ResultMessageDto> {

    private final TeamRepository teamRepository;

    @Autowired
    ResultServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public List<String> process(ResultMessageDto message) {
        ResultMessageDto.ResultDto resultDto = message.getData();
        TeamStatistics homeTeam = getTeam(resultDto.getHomeTeam());
        TeamStatistics awayTeam = getTeam(resultDto.getAwayTeam());
        saveTeamStatistics(homeTeam, resultDto.getHomeScore(), resultDto.getAwayScore());
        saveTeamStatistics(awayTeam, resultDto.getAwayScore(), resultDto.getHomeScore());
        return List.of(homeTeam.getSimplifiedStatistics(), awayTeam.getSimplifiedStatistics());
    }

    private TeamStatistics getTeam(String teamName) {
        return teamRepository.findTeamStatisticsByName(teamName).orElse(new TeamStatistics(teamName));
    }

    private void saveTeamStatistics(TeamStatistics teamStatistics, int scored, int conceded) {
        int goalDifference = scored - conceded;
        updateLastResults(teamStatistics, goalDifference);
        teamStatistics.updatePoints(goalDifference);
        teamStatistics.setPlayedEventsCount(teamStatistics.getPlayedEventsCount() + 1);
        teamStatistics.setGoalsScored(teamStatistics.getGoalsScored() + scored);
        teamStatistics.setGoalsConceded(teamStatistics.getGoalsConceded() + conceded);
        teamRepository.save(teamStatistics);
    }

    private void updateLastResults(TeamStatistics teamStatistics, int goalDifference) {
        LastResults lastResults = teamStatistics.getLastResults();
        lastResults.update(goalDifference);
        teamStatistics.setLastResults(lastResults);
    }
}
