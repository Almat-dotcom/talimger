package kz.talimger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "forgot_password")
public class ForgotPassword extends BaseEntity {

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    private Boolean isVerified = false;

    @OneToOne
    private User user;
}
