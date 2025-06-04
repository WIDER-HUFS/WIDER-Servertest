package ac.kr.hufs.wider.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO;
import ac.kr.hufs.wider.model.Entity.BloomProgressStats;

@Repository
public interface BloomProgressStatsRepository extends JpaRepository<BloomProgressStats, Long> {
    // 기존 메서드는 그대로 둡니다.
    List<BloomProgressStats> findByUserUserId(String userId);

     @Query(
        "SELECT new ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO(" +
        "    b.completedMonth, " +
        "    b.finalBloomLevel, " +
        "    COUNT(b) " +
        ") " +
        "FROM BloomProgressStats b " +
        "WHERE b.user.userId = :userId " +
        "GROUP BY b.completedMonth, b.finalBloomLevel " +
        "ORDER BY b.completedMonth, b.finalBloomLevel"
    )
    List<MonthlyBloomLevelCountDTO> findMonthlyBloomLevelStatsByUser(
        @Param("userId") String userId
    );

    /**
     * 하루 단위로 사용자가 답변한 finalBloomLevel 중 최대값(max_level)을 구하기 위한 네이티브 쿼리
     *
     * @param userId : 조회할 사용자 ID
     * @param year   : 연도 (예: 2025)
     * @param month  : 월   (1~12)
     * @return List<Object[]> :
     *      Object[0] = java.sql.Date (recorded_at 날짜: YYYY-MM-DD)
     *      Object[1] = Integer        (그날의 최대 final_bloom_level)
     */
    @Query(
      value = "SELECT DATE(b.recorded_at) AS day, " +
              "       MAX(b.final_bloom_level) AS max_level " +
              "FROM bloom_progress_stats b " +
              "WHERE b.user_id = :userId " +
              "  AND YEAR(b.recorded_at) = :year " +
              "  AND MONTH(b.recorded_at) = :month " +
              "GROUP BY DATE(b.recorded_at) " +
              "ORDER BY DATE(b.recorded_at)",
      nativeQuery = true
    )
    List<Object[]> findDailyMaxLevelByUserAndYearAndMonth(
        @Param("userId") String userId,
        @Param("year") int year,
        @Param("month") int month
    );
}
