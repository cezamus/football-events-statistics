package dev.cm.football_events_statistics.repository;

import dev.cm.football_events_statistics.model.LastResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastResultsRepository extends JpaRepository<LastResults, Long> {
}
