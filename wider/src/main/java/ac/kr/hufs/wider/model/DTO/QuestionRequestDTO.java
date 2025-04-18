package ac.kr.hufs.wider.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO { // 사용자의 답변 제출을 위한 DTO (POST)
    private String sessionId;
    private int bloomLevel;
    @NotBlank
    private String userAnswer;
}
