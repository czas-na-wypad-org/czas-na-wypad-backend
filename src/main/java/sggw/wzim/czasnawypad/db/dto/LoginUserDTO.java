package sggw.wzim.czasnawypad.db.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {
    private String login;
    private String password;
}
