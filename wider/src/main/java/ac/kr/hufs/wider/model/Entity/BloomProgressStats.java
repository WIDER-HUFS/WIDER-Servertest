package ac.kr.hufs.wider.model.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bloom_progress_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloomProgressStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본키

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionLog session; // 어떤 세션

    @Column(name = "completed_month", nullable = false)
    private LocalDate completedMonth; // 2025-04-01 등 월 기준 통계

    // 마지막으로 도달한 Bloom 단계(1~6)
    @Column(name = "final_bloom_level", nullable = false)
    private Integer finalBloomLevel; // 최종 도달한 사고 단계

    // 기록 시각
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt = LocalDateTime.now(); // 기록 시각
}
