package ac.kr.hufs.wider.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationHistoryDTO {
    private String sessionId;
    private int messageOrder;
    private String speaker;
    private String content;
    private LocalDateTime timestamp;
}
