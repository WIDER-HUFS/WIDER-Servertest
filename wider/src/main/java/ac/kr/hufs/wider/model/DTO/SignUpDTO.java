package ac.kr.hufs.wider.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String userId;
    private String password;
    private String birthDate;
    private String gender;
}
