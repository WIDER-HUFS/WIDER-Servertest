package ac.kr.hufs.wider.model.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    private String reportId; // 리포트 ID

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionLog session; // 어떤 세션에 대한 리포트인지

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 리포트를 생성한 사용자

    private String topic; // 주제명
    @Column(columnDefinition = "TEXT")
    private String summary; // 사용자의 발화 요약

    @Column(columnDefinition = "TEXT")
    private String strengths; // 잘한 점

    @Column(columnDefinition = "TEXT")
    private String weaknesses; // 부족한 점

    @Column(columnDefinition = "TEXT")
    private String suggestions; // 개선 제안

    @Column(columnDefinition = "TEXT")
    private String revisedSuggestion; // 수정된 예시 문장

    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시각
}
