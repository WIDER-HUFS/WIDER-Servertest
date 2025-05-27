package ac.kr.hufs.wider.controller;

import ac.kr.hufs.wider.model.DTO.MonthlyBloomLevelCountDTO;
import ac.kr.hufs.wider.model.Service.StatisticsService;
import ac.kr.hufs.wider.model.Entity.Users;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    /** GET /api/statistics/histogram
      * @return 사용자별 월별·레벨별 Bloom 단계 건수 리스트
      */
    @GetMapping("/histogram")
    public ResponseEntity<List<MonthlyBloomLevelCountDTO>> getHistogram(
            @AuthenticationPrincipal Users currentUser) {

        String userId = currentUser.getUserId();  // Users 엔티티의 PK가 String userId 라면
        List<MonthlyBloomLevelCountDTO> data = service.getMonthlyHistogram(userId);
        return ResponseEntity.ok(data);
    }
}
