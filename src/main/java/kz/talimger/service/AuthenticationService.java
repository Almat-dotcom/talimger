package kz.talimger.service;

import kz.talimger.dto.authentication.JwtAuthenticationResponse;
import kz.talimger.dto.authentication.RefreshTokenRequest;
import kz.talimger.dto.authentication.SignInRequest;
import kz.talimger.dto.authentication.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
