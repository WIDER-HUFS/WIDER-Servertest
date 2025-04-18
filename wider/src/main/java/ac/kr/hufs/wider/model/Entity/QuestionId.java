package ac.kr.hufs.wider.model.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionId implements Serializable{
    private String sessionId;
    private int bloomLevel;
}
