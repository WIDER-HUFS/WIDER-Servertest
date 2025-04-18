package ac.kr.hufs.wider.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionLogResponseDTO {
    private String sessionId;
    private String userId;
    private String topic;
    private LocalDateTime startedAt;
    private boolean completed;
    private LocalDateTime completedAt;
}
