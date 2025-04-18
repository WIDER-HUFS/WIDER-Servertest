package ac.kr.hufs.wider.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO { // 클라이언트에게 질문 전달 (GET)
    private String sessionId;
    private int bloomLevel;
    private String question;
    private boolean isAnswered;
}
