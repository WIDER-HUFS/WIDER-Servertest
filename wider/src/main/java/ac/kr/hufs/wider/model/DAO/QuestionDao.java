package ac.kr.hufs.wider.model.DAO;

import java.util.List;
import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.Question;

public interface QuestionDao {
    Optional<Question> findQuestion(String sessionId, int level);
    List<Question> findAllBySession(String sessionId);
    Question save(Question q);
}
