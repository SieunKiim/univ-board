package com.sieun.univboard.login;

import com.sieun.univboard.user.User;
import com.sieun.univboard.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean save(User user) {
        if (userRepository.existsByUserId(user.getUserId())) { // 중복 확인
            return false;
        } else {
            userRepository.save(user);
            log.info("저장됨 | " + String.valueOf(user));
            return true;
        }
    }

    public boolean noEmptyInstance(User user) { // 비어있는 값이 있는지 확인 (비어있으면 false 반환)
        if (user.getUserId().length() != 0 && user.getEmail().length() != 0 && user.getNickName().length() != 0 && user.getPwd().length() != 0) {
            return true;
        }
        return false;

    }

    public boolean signIn(LoginSignInDTO loginSignInDTO) {
        String userId = loginSignInDTO.getUserId();
        String pwd = loginSignInDTO.getPwd();
        String hashedPwd = makeHash(pwd);
        if (userRepository.existsById(userId)) { // 아이디 존재
            log.info(userRepository.findById(userId) + " " + hashedPwd + " " + pwd);
            User findById = userRepository.getReferenceById(userId);
            if (findById.getPwd().equals(hashedPwd)) {
                log.info("로그인 완료");
                return true;
            } else {
                log.info("비밀번호 틀림");
                return false;
            }
        } else { // 아이디 없음
            log.info("아이디 없음");
            return false;
        }
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
