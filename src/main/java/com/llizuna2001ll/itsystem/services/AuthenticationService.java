package com.llizuna2001ll.itsystem.services;


import com.llizuna2001ll.itsystem.dto.AuthenticationRequest;
import com.llizuna2001ll.itsystem.dto.AuthenticationResponse;
import com.llizuna2001ll.itsystem.dto.RegisterRequest;
import com.llizuna2001ll.itsystem.entities.User;
import com.llizuna2001ll.itsystem.enums.Role;
import com.llizuna2001ll.itsystem.exceptions.UsernameOrPasswordIncorrectException;
import com.llizuna2001ll.itsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (RuntimeException e) {
            throw new UsernameOrPasswordIncorrectException("Incorrect username or password");
        }
        var user = userRepository.findByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


}
