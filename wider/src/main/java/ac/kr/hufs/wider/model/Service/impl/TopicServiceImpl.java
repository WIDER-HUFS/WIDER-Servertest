package ac.kr.hufs.wider.model.Service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.DailyTopic;
import ac.kr.hufs.wider.model.Repository.TopicRepository;
import ac.kr.hufs.wider.model.Service.TopicService;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public DailyTopic createTopic(DailyTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Optional<DailyTopic> getTopicByDate(LocalDate date) {
        return topicRepository.findById(date);
    }

    @Override
    public List<DailyTopic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public DailyTopic updateTopic(DailyTopic topic) {
        if (!topicRepository.existsById(topic.getTopicDate())) {
            throw new IllegalArgumentException("Topic not found for date: " + topic.getTopicDate());
        }
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(LocalDate date) {
        topicRepository.deleteById(date);
    }

    @Override
    public Optional<DailyTopic> getTodayTopic() {
        return topicRepository.findFirstByOrderByTopicDateDesc();
    }
} 