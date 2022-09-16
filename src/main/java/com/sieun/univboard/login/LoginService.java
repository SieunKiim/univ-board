package com.sieun.univboard.login;

import com.sieun.univboard.user.User;

public interface LoginService {
    boolean save(User user);

    boolean noEmptyInstance(User user);

    String makeHash(String pwd);


}
