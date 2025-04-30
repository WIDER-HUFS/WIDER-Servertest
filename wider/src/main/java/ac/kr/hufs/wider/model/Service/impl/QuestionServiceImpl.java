package ac.kr.hufs.wider.model.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.Question;
import ac.kr.hufs.wider.model.Entity.QuestionId;
import ac.kr.hufs.wider.model.Repository.QuestionRepository;
import ac.kr.hufs.wider.model.Service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionsBySessionId(String sessionId) {
        return questionRepository.findBySessionIdOrderByBloomLevelAsc(sessionId);
    }

    @Override
    public Optional<Question> getQuestionBySessionIdAndBloomLevel(String sessionId, int bloomLevel) {
        return questionRepository.findBySessionIdAndBloomLevel(sessionId, bloomLevel);
    }

    @Override
    public Question updateQuestion(Question question) {
        QuestionId questionId = new QuestionId(question.getSessionId(), question.getBloomLevel());
        if (!questionRepository.existsById(questionId)) {
            throw new IllegalArgumentException("Question not found with sessionId: " + question.getSessionId() + 
                " and bloomLevel: " + question.getBloomLevel());
        }
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(QuestionId questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public void deleteQuestionsBySessionId(String sessionId) {
        List<Question> questions = getQuestionsBySessionId(sessionId);
        questionRepository.deleteAll(questions);
    }
} 