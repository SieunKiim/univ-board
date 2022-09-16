package com.sieun.univboard.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginSignInDTO {
    private String userId;
    private String pwd;
}
