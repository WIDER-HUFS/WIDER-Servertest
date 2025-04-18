package ac.kr.hufs.wider.model.DAO;

import java.util.List;

import ac.kr.hufs.wider.model.Entity.ConversationHistory;

public interface ConversationHistoryDao {
    List<ConversationHistory> findAll(String sessionId);
    int countBySession(String sessionId);
    ConversationHistory save(ConversationHistory history);
}
