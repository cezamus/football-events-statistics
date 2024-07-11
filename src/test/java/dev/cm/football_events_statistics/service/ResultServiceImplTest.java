package dev.cm.football_events_statistics.service;

import dev.cm.football_events_statistics.dto.ResultMessageDto;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    private static final String TYPE = "RESULT";
    private static final String TEAM_A = "a";
    private static final String TEAM_B = "b";
    private static final ResultMessageDto RESULT_MESSAGE
            = new ResultMessageDto(TYPE, new ResultMessageDto.ResultDto(TEAM_A, TEAM_B, 2, 0));
    private static final TeamStatistics TEAM_A_AFTER_ONE_EVENT
            = new TeamStatistics(TEAM_A, new LastResults(), 1, 3, 2, 0);
    private static final TeamStatistics TEAM_B_AFTER_ONE_EVENT
            = new TeamStatistics(TEAM_B, new LastResults(), 1, 0, 0, 2);
    private static final String TEAM_A_STATISTICS_AFTER_ONE_EVENT = "a 1 3 2 0";
    private static final String TEAM_B_STATISTICS_AFTER_ONE_EVENT = "b 1 0 0 2";
    private static final String TEAM_A_STATISTICS_AFTER_TWO_EVENTS = "a 2 6 4 0";
    private static final String TEAM_B_STATISTICS_AFTER_TWO_EVENTS = "b 2 0 0 4";

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private ResultServiceImpl resultService;

    @Test
    void shouldCreateAndReturnStatisticsWhenGivenNewTeams() {
        // given
        when(teamRepository.findTeamStatisticsByName(any())).thenReturn(Optional.empty());
        // when
        List<String> statistics = resultService.process(RESULT_MESSAGE);
        //then
        Assertions.assertEquals(
                List.of(TEAM_A_STATISTICS_AFTER_ONE_EVENT, TEAM_B_STATISTICS_AFTER_ONE_EVENT), statistics);
    }

    @Test
    void shouldUpdateAndReturnStatisticsWhenGivenExistingTeams() {
        // given
        when(teamRepository.findTeamStatisticsByName(TEAM_A)).thenReturn(Optional.of(TEAM_A_AFTER_ONE_EVENT));
        when(teamRepository.findTeamStatisticsByName(TEAM_B)).thenReturn(Optional.of(TEAM_B_AFTER_ONE_EVENT));
        // when
        List<String> statistics = resultService.process(RESULT_MESSAGE);
        //then
        Assertions.assertEquals(
                List.of(TEAM_A_STATISTICS_AFTER_TWO_EVENTS, TEAM_B_STATISTICS_AFTER_TWO_EVENTS), statistics);
    }
}