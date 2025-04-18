package ac.kr.hufs.wider.model.DAO.Impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DAO.DailyTopicDao;
import ac.kr.hufs.wider.model.Entity.DailyTopic;
import ac.kr.hufs.wider.model.Repository.DailyTopicRepository;

@Service
public class DailyTopicDaoImpl implements DailyTopicDao{
    @Autowired
    private DailyTopicRepository dailyTopicRepository;

    @Override
    public Optional<DailyTopic> findByDate(LocalDate date) {
        return dailyTopicRepository.findByTopicDate(date);
    }

    @Override
    public DailyTopic save(DailyTopic topic) {
        return dailyTopicRepository.save(topic);
    }

    
}
