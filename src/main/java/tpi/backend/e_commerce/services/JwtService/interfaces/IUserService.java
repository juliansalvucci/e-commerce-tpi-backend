package tpi.backend.e_commerce.services.JwtService.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {
    UserDetailsService userDetailsService();
}
