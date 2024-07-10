package dev.cm.football_events_statistics.repository;

import dev.cm.football_events_statistics.model.TeamStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamStatistics, Long> {

    boolean existsByName(String name);

    Optional<TeamStatistics> getTeamStatisticsByName(String name);
}
