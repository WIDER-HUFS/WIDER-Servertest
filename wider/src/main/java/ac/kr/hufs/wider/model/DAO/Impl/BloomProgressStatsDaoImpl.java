package ac.kr.hufs.wider.model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DAO.BloomProgressStatsDao;
import ac.kr.hufs.wider.model.Entity.BloomProgressStats;
import ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO;
import ac.kr.hufs.wider.model.Repository.BloomProgressStatsRepository;

@Service
public class BloomProgressStatsDaoImpl implements BloomProgressStatsDao {

    @Autowired
    private BloomProgressStatsRepository bloomProgressStatsRepository;

    /** 사용자 ID로 BloomProgressStats 목록 조회 */
    @Override
    public List<BloomProgressStats> findByUserId(String userId) {
        return bloomProgressStatsRepository.findByUserUserId(userId);
    }

    /** 사용자 ID로 월별 Bloom 단계별 집계 DTO 조회 */
    @Override
    public List<MonthlyBloomLevelCountDTO> findStats(String userId) {
        return bloomProgressStatsRepository.findMonthlyBloomLevelStatsByUser(userId);
    }

    /** BloomProgressStats 저장 */
    @Override
    public BloomProgressStats save(BloomProgressStats stat) {
        return bloomProgressStatsRepository.save(stat);
    }
}
