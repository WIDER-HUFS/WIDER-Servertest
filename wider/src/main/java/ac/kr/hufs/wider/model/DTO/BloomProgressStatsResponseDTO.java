package ac.kr.hufs.wider.model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloomProgressStatsResponseDTO {
    private Long id;
    private String userId;
    private String sessionId;
    private LocalDate completedMonth;
    private int finalBloomLevel;
    private LocalDateTime recordedAt;
}
