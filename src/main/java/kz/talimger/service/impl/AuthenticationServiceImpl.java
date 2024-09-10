package kz.talimger.service.impl;

import kz.talimger.util.ErrorCodeConstant;
import kz.talimger.dto.authentication.JwtAuthenticationResponse;
import kz.talimger.dto.authentication.RefreshTokenRequest;
import kz.talimger.dto.authentication.SignInRequest;
import kz.talimger.dto.authentication.SignUpRequest;
import kz.talimger.enums.RoleEnum;
import kz.talimger.exception.KazNpuException;
import kz.talimger.mapper.StudentMapper;
import kz.talimger.model.Student;
import kz.talimger.model.User;
import kz.talimger.repository.RoleRepository;
import kz.talimger.repository.StudentRepository;
import kz.talimger.repository.UserRepository;
import kz.talimger.service.AuthenticationService;
import kz.talimger.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StudentMapper studentMapper;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getRePassword())) {
            throw new KazNpuException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.PASSWORD_NOT_EQUAL,
                    "message.error.password-not-equal", signUpRequest.getPassword());
        }

        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new KazNpuException(
                    HttpStatus.FORBIDDEN,
                    ErrorCodeConstant.USER_IS_EXIST,
                    "message.error.user-is-exist", signUpRequest.getEmail());
        }

        Student student = studentMapper.toEntity(signUpRequest);
        student.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        student.setRoles(Collections.singleton(roleRepository.findByName(RoleEnum.STUDENT.getCode())));
        student = studentRepository.save(student);
        return generateToken(student);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(), signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new KazNpuException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCodeConstant.INVALID_PASSWORD_OR_EMAIL,
                        "message.error.invalid-email-password"));
        return generateToken(user);
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    private JwtAuthenticationResponse generateToken(User user) {
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
}
