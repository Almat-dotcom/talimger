package kz.talimger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private String middleName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    public String getFullName() {
        return Stream.of(lastName, firstName, middleName)
                .filter(name -> Objects.nonNull(name) && !name.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String getInitials() {
        return Stream.of(
                        Optional.ofNullable(firstName).map(name -> name.substring(0, 1)).orElse(""),
                        Optional.ofNullable(lastName).map(name -> name.substring(0, 1)).orElse("")
                )
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(""));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public boolean isLoginCorrect(LoginRequestDTO loginRequest, PasswordEncoder passwordEncoder) {
//        return passwordEncoder.matches(loginRequest.password(),this.password);
//    }
}