package ac.kr.hufs.wider.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.ConversationHistory;
import ac.kr.hufs.wider.model.Entity.ConversationId;

@Repository
public interface ConversationHistoryRepository extends JpaRepository<ConversationHistory, ConversationId>{
    List<ConversationHistory> findBySessionIdOrderByMessageOrderAsc(String sessionId);
    int countBySessionId(String sessionId); // 메시지 순번 증가 시 필요
}
