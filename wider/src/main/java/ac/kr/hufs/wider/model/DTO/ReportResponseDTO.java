package ac.kr.hufs.wider.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDTO {
    private String reportId;
    private String sessionId;
    private String userId;
    private String topic;
    private String summary;
    private String strengths;
    private String weaknesses;
    private String suggestions;
    private String revisedSuggestion;
    private LocalDateTime createdAt;
}
