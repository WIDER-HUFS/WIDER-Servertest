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
@Table(name = "conversation_history")
@IdClass(ConversationId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationHistory {
    @Id
    private String sessionId; // 세션 ID

    @Id
    private int messageOrder; // 몇 번째 메시지인지

    @Column(nullable = false)
    private String speaker; // user or ai

    @Column(columnDefinition = "TEXT")
    private String content; // 메시지 본문
    private LocalDateTime timestamp = LocalDateTime.now(); // 발화 시점
}
