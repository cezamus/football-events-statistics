package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.GetStatisticsMessageDto;
import dev.cm.football_events_statistics.exception.TeamNotFoundException;
import dev.cm.football_events_statistics.model.LastResults;
import dev.cm.football_events_statistics.model.TeamStatistics;
import dev.cm.football_events_statistics.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceImplTest {

    private static final String TYPE = "GET_STATISTICS";
    private static final String TEAM_A = "a";
    private static final String TEAM_B = "b";
    private static final GetStatisticsMessageDto GET_STATISTICS_MESSAGE
            = new GetStatisticsMessageDto(TYPE, new GetStatisticsMessageDto.GetStatisticsDto(List.of(TEAM_A, TEAM_B)));
    private static final TeamStatistics TEAM_A_STATISTICS
            = new TeamStatistics(TEAM_A, new LastResults(), 1, 3, 2, 0);
    private static final TeamStatistics TEAM_B_STATISTICS
            = new TeamStatistics(TEAM_B, new LastResults(), 1, 0, 0, 2);
    private static final String TEAM_A_STATISTICS_AFTER_ONE_EVENT = "a W 2.0 1 3 2 0";
    private static final String TEAM_B_STATISTICS_AFTER_ONE_EVENT = "b L 2.0 1 0 0 2";


    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @Test
    void shouldCreateAndReturnClearStatisticsWhenGivenNewTeams() {
        when(teamRepository.findTeamStatisticsByName(any())).thenReturn(Optional.empty());
        assertThrows(TeamNotFoundException.class, () -> statisticsService.process(GET_STATISTICS_MESSAGE));
    }

    @Test
    void shouldReturnStatisticsWhenGivenExistingTeams() {
        // given
        TEAM_A_STATISTICS.getLastResults().update(2);
        TEAM_B_STATISTICS.getLastResults().update(-2);
        when(teamRepository.findTeamStatisticsByName(TEAM_A)).thenReturn(Optional.of(TEAM_A_STATISTICS));
        when(teamRepository.findTeamStatisticsByName(TEAM_B)).thenReturn(Optional.of(TEAM_B_STATISTICS));
        // when
        List<String> statistics = statisticsService.process(GET_STATISTICS_MESSAGE);
        //then
        Assertions.assertEquals(
                List.of(TEAM_A_STATISTICS_AFTER_ONE_EVENT, TEAM_B_STATISTICS_AFTER_ONE_EVENT), statistics);
    }
}