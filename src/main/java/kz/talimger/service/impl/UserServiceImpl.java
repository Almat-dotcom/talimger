package kz.talimger.service.impl;

import kz.talimger.exception.KazNpuException;
import kz.talimger.model.User;
import kz.talimger.repository.UserRepository;
import kz.talimger.service.UserService;
import kz.talimger.util.ErrorCodeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("message.error.user-not-found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.USER_NOT_FOUND,
                        "message.error.user-not-found", email)
        );
    }

    @Override
    public void updatePassword(String email, String password) {
        userRepository.updatePassword(email, password);
    }


}
