package ac.kr.hufs.wider.model.Repository;

import ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO;
import ac.kr.hufs.wider.model.Entity.BloomProgressStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BloomProgressStatsRepository extends JpaRepository<BloomProgressStats, Long> {

    // 기존 Map 반환 메서드는 삭제하거나 주석 처리하세요

    @Query("""
        SELECT new ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDto(
             b.completedMonth,
             b.finalBloomLevel,
             COUNT(b)
        )
        FROM BloomProgressStats b
        WHERE b.user.userId = :userId
        GROUP BY b.completedMonth, b.finalBloomLevel
        ORDER BY b.completedMonth, b.finalBloomLevel
    """)
    List<MonthlyBloomLevelCountDTO> findMonthlyBloomLevelStatsByUser(
        @Param("userId") String userId
    );
}
