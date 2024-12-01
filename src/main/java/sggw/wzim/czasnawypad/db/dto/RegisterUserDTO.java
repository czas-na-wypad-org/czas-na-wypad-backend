package sggw.wzim.czasnawypad.db.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
}
