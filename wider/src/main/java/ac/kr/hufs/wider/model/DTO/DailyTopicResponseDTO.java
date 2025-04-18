package ac.kr.hufs.wider.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTopicResponseDTO {
    private String topic;
    private String topicPrompt;
    private LocalDate topicDate;
}
