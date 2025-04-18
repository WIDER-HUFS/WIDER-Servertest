package ac.kr.hufs.wider.model.DAO.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DAO.QuestionDao;
import ac.kr.hufs.wider.model.Entity.Question;
import ac.kr.hufs.wider.model.Repository.QuestionRepository;

@Service
public class QuestionDaoImpl implements QuestionDao{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAllBySession(String sessionId) {
        return questionRepository.findBySessionIdOrderByBloomLevelAsc(sessionId);
    }

    @Override
    public Optional<Question> findQuestion(String sessionId, int level) {
        return questionRepository.findBySessionIdAndBloomLevel(sessionId, level);
    }

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    
}
