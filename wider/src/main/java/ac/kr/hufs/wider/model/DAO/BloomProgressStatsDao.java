package ac.kr.hufs.wider.model.DAO;

import java.util.List;
import java.util.Map;

import ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO;
import ac.kr.hufs.wider.model.Entity.BloomProgressStats;

public interface BloomProgressStatsDao {
    List<BloomProgressStats> findByUserId(String userId);
List<MonthlyBloomLevelCountDTO> findStats(String userId);
BloomProgressStats save(BloomProgressStats stat);

}
