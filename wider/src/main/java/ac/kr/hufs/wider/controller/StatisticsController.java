// src/main/java/ac/kr/hufs/wider/controller/StatisticsController.java

package ac.kr.hufs.wider.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ac.kr.hufs.wider.model.DTO.MonthlyLevelHistogramDTO;
import ac.kr.hufs.wider.model.Entity.Users;
import ac.kr.hufs.wider.model.Service.StatisticsService;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {
    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    /**
     * GET /api/statistics/histogram
     * 
     * @param currentUser 인증된 Users 엔티티
     * @param year        조회 연도 (예: 2025) — 필수 요청 파라미터로 변경
     * @return List<MonthlyLevelHistogramDTO> (1월부터 12월까지 리스트)
     */
    @GetMapping("/histogram")
    public ResponseEntity<List<MonthlyLevelHistogramDTO>> getHistogram(
            @AuthenticationPrincipal Users currentUser,
            @RequestParam int year) {

        String userId = currentUser.getUserId();
        List<MonthlyLevelHistogramDTO> data = service.getMonthlyHistogram(userId, year);
        return ResponseEntity.ok(data);
    }

    /**
     * 테스트용 엔드포인트: userId와 year를 쿼리 파라미터로 직접 전달
     * 예시: GET /api/statistics/histogram/test?userId=john123&year=2025
     */
    @GetMapping("/histogram/test")
    public ResponseEntity<List<MonthlyLevelHistogramDTO>> getHistogramTest(
            @RequestParam String userId,
            @RequestParam int year) {
        return ResponseEntity.ok(service.getMonthlyHistogram(userId, year));
    }
}
