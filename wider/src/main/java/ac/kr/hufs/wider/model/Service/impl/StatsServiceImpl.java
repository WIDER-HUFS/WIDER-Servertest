package ac.kr.hufs.wider.model.Service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.BloomProgressStats;
import ac.kr.hufs.wider.model.Repository.StatsRepository;
import ac.kr.hufs.wider.model.Service.StatsService;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Autowired
    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public BloomProgressStats createStats(BloomProgressStats stats) {
        return statsRepository.save(stats);
    }

    @Override
    public Optional<BloomProgressStats> getStatsById(Long id) {
        return statsRepository.findById(id);
    }

    @Override
    public List<BloomProgressStats> getStatsByUserId(String userId) {
        return statsRepository.findByUserId(userId);
    }

    @Override
    public Optional<BloomProgressStats> getStatsBySessionId(String sessionId) {
        return statsRepository.findBySessionId(sessionId);
    }

    @Override
    public List<BloomProgressStats> getStatsByUserIdAndMonth(String userId, LocalDate month) {
        return statsRepository.findByUserIdAndCompletedMonth(userId, month);
    }

    @Override
    public BloomProgressStats updateStats(BloomProgressStats stats) {
        if (!statsRepository.existsById(stats.getId())) {
            throw new IllegalArgumentException("Stats not found with id: " + stats.getId());
        }
        return statsRepository.save(stats);
    }

    @Override
    public void deleteStats(Long id) {
        statsRepository.deleteById(id);
    }

    @Override
    public void deleteStatsByUserId(String userId) {
        List<BloomProgressStats> stats = getStatsByUserId(userId);
        statsRepository.deleteAll(stats);
    }
} 