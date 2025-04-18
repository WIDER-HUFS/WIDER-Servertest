package ac.kr.hufs.wider.model.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.DailyTopic;

@Repository
public interface DailyTopicRepository extends JpaRepository<DailyTopic, LocalDate>{
    Optional<DailyTopic> findByTopicDate(LocalDate topicDate);
}
