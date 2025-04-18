package ac.kr.hufs.wider.model.DAO;

import java.time.LocalDate;
import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.DailyTopic;

public interface DailyTopicDao {
    Optional<DailyTopic> findByDate(LocalDate date);
    DailyTopic save(DailyTopic topic);
}
