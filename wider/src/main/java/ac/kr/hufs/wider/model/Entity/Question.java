package ac.kr.hufs.wider.model.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@IdClass(QuestionId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String sessionId; // 어떤 세션에서 나온 질문인지
    @Id
    private int bloomLevel; // Bloom 단계

    private String topic; // 질문이 속한 주제

    @Column(nullable = false)
    private String question; // 실제 질문 내용
    private boolean inAnswered = false; // 사용자 응답 여부
    private String userAnswer; // 사용자의 실제 답변
    private LocalDateTime createdAt = LocalDateTime.now(); // 질문 생성 시간
}
