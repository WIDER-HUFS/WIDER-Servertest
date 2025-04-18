package ac.kr.hufs.wider.model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DAO.ConversationHistoryDao;
import ac.kr.hufs.wider.model.Entity.ConversationHistory;
import ac.kr.hufs.wider.model.Repository.ConversationHistoryRepository;

@Service
public class ConversationHistoryDaoImpl implements ConversationHistoryDao{
    @Autowired
    private ConversationHistoryRepository conversationHistoryRepository;

    @Override
    public int countBySession(String sessionId) {
        return conversationHistoryRepository.countById_SessionId(sessionId);
    }

    @Override
    public List<ConversationHistory> findAll(String sessionId) {
        return conversationHistoryRepository.findById_SessionIdOrderById_MessageOrderAsc(sessionId);
    }

    @Override
    public ConversationHistory save(ConversationHistory history) {
        return conversationHistoryRepository.save(history);
    }

    

}
