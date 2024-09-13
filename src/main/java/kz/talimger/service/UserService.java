package kz.talimger.service;

import kz.talimger.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User findUserByEmail(String email);

    void updatePassword(String email, String password);
}
