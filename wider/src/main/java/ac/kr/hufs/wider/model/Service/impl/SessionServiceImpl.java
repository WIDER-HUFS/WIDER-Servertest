package ac.kr.hufs.wider.model.Service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.SessionLog;
import ac.kr.hufs.wider.model.Repository.SessionRepository;
import ac.kr.hufs.wider.model.Service.SessionService;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionLog createSession(SessionLog session) {
        return sessionRepository.save(session);
    }

    @Override
    public Optional<SessionLog> getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public List<SessionLog> getSessionsByUserId(String userId) {
        return sessionRepository.findByUserId(userId);
    }

    @Override
    public SessionLog updateSession(SessionLog session) {
        if (!sessionRepository.existsById(session.getSessionId())) {
            throw new IllegalArgumentException("Session not found with id: " + session.getSessionId());
        }
        return sessionRepository.save(session);
    }

    @Override
    public SessionLog completeSession(String sessionId) {
        SessionLog session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("Session not found with id: " + sessionId));
        
        session.setCompleted(true);
        session.setCompletedAt(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public void deleteSessionsByUserId(String userId) {
        List<SessionLog> sessions = getSessionsByUserId(userId);
        sessionRepository.deleteAll(sessions);
    }
} 