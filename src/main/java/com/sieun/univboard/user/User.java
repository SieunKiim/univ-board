package com.sieun.univboard.user;


import lombok.*;

import javax.persistence.*;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
//@Table  (엔티티 이름과 클래스 이름이 같으면 생략 가능함)
public class User {
    @Id
    @Column(nullable = false, length = 50)
    private String userId;

     // 이름은 변경 가능
    @Column(nullable = false, length = 50)
    private String nickName;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String email;

}
