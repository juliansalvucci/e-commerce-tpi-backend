package tpi.backend.e_commerce.services.JwtService.interfaces;

import tpi.backend.e_commerce.models.auth.request.SignInRequest;
import tpi.backend.e_commerce.models.auth.request.SignUpRequest;
import tpi.backend.e_commerce.models.auth.response.JwtAuthenticationResponse;

public interface IAuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
