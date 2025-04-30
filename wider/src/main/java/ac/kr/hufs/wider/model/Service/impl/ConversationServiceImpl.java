package ac.kr.hufs.wider.model.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.ConversationHistory;
import ac.kr.hufs.wider.model.Entity.ConversationId;
import ac.kr.hufs.wider.model.Repository.ConversationRepository;
import ac.kr.hufs.wider.model.Service.ConversationService;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public ConversationHistory createConversation(ConversationHistory conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Optional<ConversationHistory> getConversationById(ConversationId conversationId) {
        return conversationRepository.findById(conversationId);
    }

    @Override
    public List<ConversationHistory> getConversationsBySessionId(String sessionId) {
        return conversationRepository.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }

    @Override
    public ConversationHistory updateConversation(ConversationHistory conversation) {
        if (!conversationRepository.existsById(conversation.getId())) {
            throw new IllegalArgumentException("Conversation not found with id: " + conversation.getId());
        }
        return conversationRepository.save(conversation);
    }

    @Override
    public void deleteConversation(ConversationId conversationId) {
        conversationRepository.deleteById(conversationId);
    }

    @Override
    public void deleteConversationsBySessionId(String sessionId) {
        List<ConversationHistory> conversations = getConversationsBySessionId(sessionId);
        conversationRepository.deleteAll(conversations);
    }
} 