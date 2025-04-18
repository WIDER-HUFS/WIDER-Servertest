package ac.kr.hufs.wider.model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.Question;
import ac.kr.hufs.wider.model.Entity.QuestionId;

@Repository
public interface QuestionRepository extends JpaRepository<Question, QuestionId>{
    List<Question> findBySessionIdOrderByBloomLevelAsc(String sessionId);
    Optional<Question> findBySessionIdAndBloomLevel(String sessionId, int bloomLevel);
}
