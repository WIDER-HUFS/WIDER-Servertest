package ac.kr.hufs.wider.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationId {
    private String sessionId; // 세션 ID
    private int messageOrder;
}
