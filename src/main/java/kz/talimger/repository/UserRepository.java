package kz.talimger.repository;

import jakarta.transaction.Transactional;
import kz.talimger.model.Role;
import kz.talimger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByRoles(Set<Role> role);

    @Transactional
    @Modifying
    @Query("update User u set u.password =?2 where u.email=?1")
    void updatePassword(String email, String password);
} 