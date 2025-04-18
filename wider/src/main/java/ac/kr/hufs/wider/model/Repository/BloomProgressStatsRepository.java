package ac.kr.hufs.wider.model.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.BloomProgressStats;

@Repository
public interface BloomProgressStatsRepository extends JpaRepository<BloomProgressStats, Long>{
    List<BloomProgressStats> findByUser_UserId(String userId);

    @Query("""
        SELECT NEW map(
            MONTH(b.completedMonth) AS month,
            b.finalBloomLevel AS level,
            COUNT(b) AS count
        )
        FROM BloomProgressStats b
        WHERE MONTH(b.completedMonth) = :month
        GROUP BY b.completedMonth, b.finalBloomLevel
        ORDER BY b.completedMonth, b.finalBloomLevel
    """)
    List<Map<String, Object>> findMonthlyBloomLevelStats(@Param("month") int month);
}
