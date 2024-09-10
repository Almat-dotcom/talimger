package kz.talimger.dto.authentication;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
