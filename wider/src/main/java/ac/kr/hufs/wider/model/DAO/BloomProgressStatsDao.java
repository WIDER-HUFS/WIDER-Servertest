package ac.kr.hufs.wider.model.DAO;

import java.util.List;
import java.util.Map;

import ac.kr.hufs.wider.model.Entity.BloomProgressStats;

public interface BloomProgressStatsDao {
    BloomProgressStats save(BloomProgressStats stat);
    List<BloomProgressStats> findByUserId(String userId);
    List<Map<String, Object>> findStats(int month);
}
