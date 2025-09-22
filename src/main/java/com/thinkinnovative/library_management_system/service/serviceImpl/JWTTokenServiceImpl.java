package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.AuthRequestDTO;
import com.thinkinnovative.library_management_system.entity.User;
import com.thinkinnovative.library_management_system.repository.UserRepository;
import com.thinkinnovative.library_management_system.security.JWTUtil;
//import com.thinkinnovative.library_management_system.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JWTTokenServiceImpl {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public JWTTokenServiceImpl(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String login(AuthRequestDTO authRequest) {
        Optional<User> userOptional = userRepository.findByUsername(authRequest.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user.getUsername()); // Return JWT Token
            }
        }
        throw new RuntimeException("Invalid Credentials");
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash Password
        userRepository.save(user);
    }
}
