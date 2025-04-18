package ac.kr.hufs.wider.model.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "daily_topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTopic {
    @Id
    private LocalDate topicDate; //주제가 지정된 날짜

    @Column(nullable = false)
    private String topic; // 주제명 (예: 기본소득)
    
    private String topicPrompt; // 사용자에게 보여질 기본 질문(예: 기본소득은 개인의 생산력에 도움이 될까?)
    private LocalDateTime createdAt = LocalDateTime.now(); // 주제 등록 시각
}
