package ac.kr.hufs.wider.model.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationHistory {
    
    @EmbeddedId
    private ConversationId id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @MapsId("sessionId")
    private SessionLog sessionLog;

    @Column(nullable = false)
    private String speaker; // user or ai

    @Column(columnDefinition = "TEXT")
    private String content; // 메시지 본문

    private LocalDateTime timestamp = LocalDateTime.now(); // 발화 시점
}
