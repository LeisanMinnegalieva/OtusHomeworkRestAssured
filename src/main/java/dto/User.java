package dto;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class User {

    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
