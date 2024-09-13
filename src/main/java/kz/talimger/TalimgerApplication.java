package kz.talimger;

import kz.talimger.enums.RoleEnum;
import kz.talimger.model.User;
import kz.talimger.repository.RoleRepository;
import kz.talimger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class TalimgerApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(TalimgerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<User> adminAccount = userRepository.findByRoles(Collections.singleton(roleRepository.findByName(RoleEnum.ADMIN.getCode())));
        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setFirstName("ADMIN");
            user.setLastName("ADMIN");
            user.setRoles(Collections.singleton(roleRepository.findByName(RoleEnum.ADMIN.getCode())));
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}
