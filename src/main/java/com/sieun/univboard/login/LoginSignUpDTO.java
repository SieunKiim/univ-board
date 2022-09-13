package com.sieun.univboard.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginSignUpDTO {
    private String userId;
    private String nickName;
    private String pwd;
    private String email;
}

