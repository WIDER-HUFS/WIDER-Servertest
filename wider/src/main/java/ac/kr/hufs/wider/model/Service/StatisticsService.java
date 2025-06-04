// src/main/java/ac/kr/hufs/wider/model/Service/StatisticsService.java

package ac.kr.hufs.wider.model.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DTO.MonthlyLevelHistogramDTO;
import ac.kr.hufs.wider.model.Repository.BloomProgressStatsRepository;

@Service
public class StatisticsService {
    private final BloomProgressStatsRepository repo;

    public StatisticsService(BloomProgressStatsRepository repo) {
        this.repo = repo;
    }

    /**
     * 사용자가 매일 답변한 finalBloomLevel 중 최대값(maxLevel)을 기준으로,
     * 그 날 도달한 maxLevel 이하(1~maxLevel)까지 모두 +1 씩 누적해
     * 월별로 레벨별 일수 통계를 계산하여 반환한다.
     * 
     * @param userId 조회할 사용자 ID
     * @param year   조회 연도 (예: 2025)
     * @return List<MonthlyLevelHistogramDTO> length = 12
     *         각 인덱스(i=0→1월, i=5→6월 …)가 MonthlyLevelHistogramDTO 로 매핑되어 있음
     */
    public List<MonthlyLevelHistogramDTO> getMonthlyHistogram(String userId, int year) {
        // 1) 1월~12월까지 초기화
        List<MonthlyLevelHistogramDTO> allMonths = new ArrayList<>();
        for (int m = 1; m <= 12; m++) {
            allMonths.add(new MonthlyLevelHistogramDTO(m));
        }

        // 2) 각 월(month)별로 Repository 호출 → 일별 maxLevel 추출 → 누적
        for (int month = 1; month <= 12; month++) {
            // 하루 단위 maxLevel을 가져오는 쿼리 결과: List<Object[]> (day, maxLevel)
            List<Object[]> rawDaily = repo.findDailyMaxLevelByUserAndYearAndMonth(userId, year, month);

            // rawDaily 를 순회하며 “maxLevel” 이하 인덱스에 +1
            MonthlyLevelHistogramDTO dto = allMonths.get(month - 1);
            for (Object[] row : rawDaily) {
                // row[0] = java.sql.Date(YYYY-MM-DD), row[1] = Integer(max_level)
                Integer maxLevel = ((Number) row[1]).intValue();
                if (maxLevel == null || maxLevel < 1) {
                    // 해당 일에 답변이 없거나 level<1이면 패스
                    continue;
                }
                // 1~maxLevel까지 +1
                for (int lvl = 1; lvl <= maxLevel && lvl <= 6; lvl++) {
                    dto.getCounts()[lvl - 1] += 1;
                }
            }
        }

        return allMonths;
    }
}
