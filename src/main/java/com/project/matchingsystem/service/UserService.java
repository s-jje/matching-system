package com.project.matchingsystem.service;

import com.project.matchingsystem.domain.User;
import com.project.matchingsystem.domain.UserRoleEnum;
import com.project.matchingsystem.dto.ResponseStatusDto;
import com.project.matchingsystem.dto.SignInRequestDto;
import com.project.matchingsystem.dto.SignUpRequestDto;
import com.project.matchingsystem.exception.ErrorCode;
import com.project.matchingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public ResponseStatusDto signUp(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_USERNAME.getMessage());
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (signUpRequestDto.isAdmin()) {
            if (!signUpRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException(ErrorCode.INVALID_AUTH_TOKEN.getMessage());
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, role);
        userRepository.save(user);
        return new ResponseStatusDto(HttpStatus.OK.toString(), "회원가입을 완료하였습니다.");
    }

    public ResponseStatusDto signIn(SignInRequestDto signInRequestDto) {
        return null;
    }

    public ResponseStatusDto signOut() {
        return null;
    }

    public ResponseStatusDto applySellerRole() {
        return null;
    }

}
