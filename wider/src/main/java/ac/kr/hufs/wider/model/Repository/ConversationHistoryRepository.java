package ac.kr.hufs.wider.model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.ConversationHistory;
import ac.kr.hufs.wider.model.Entity.ConversationId;

@Repository
public interface ConversationHistoryRepository extends JpaRepository<ConversationHistory, ConversationId>{
    // 복합키 전체로 조회
    Optional<ConversationHistory> findById(ConversationId id);
    List<ConversationHistory> findById_SessionIdOrderById_MessageOrderAsc(String sessionId);
    int countById_SessionId(String sessionId); // 메시지 순번 증가 시 필요
}
