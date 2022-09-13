package com.sieun.univboard.login;

import com.sieun.univboard.user.User;
import com.sieun.univboard.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean save(User user) {
        if (userExists(user.getUserId()).isEmpty()) { // 중복 확인
            userRepository.save(user);
            log.info("저장됨 | " + String.valueOf(user));
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> userExists(String userId) {
        return userRepository.findById(userId);
    }

    public String makeHash(String pwd) { // 비밀번호 해싱
        return String.valueOf(pwd.hashCode());
//        SHA-256 방식의 해싱 -> 너무 길어서 데이터가 들어가지 않음
//        try{
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
//            StringBuilder hexString = new StringBuilder();
//
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//
//            //출력
//            return hexString.toString();
//
//        } catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
    }

}
