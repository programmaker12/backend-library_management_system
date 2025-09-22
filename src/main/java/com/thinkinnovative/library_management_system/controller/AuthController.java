package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.ApiResponse;
import com.thinkinnovative.library_management_system.dto.AuthRequestDTO;
import com.thinkinnovative.library_management_system.dto.LoginRequestDTO;
import com.thinkinnovative.library_management_system.dto.LoginResponseDTO;
import com.thinkinnovative.library_management_system.entity.User;
import com.thinkinnovative.library_management_system.repository.UserRepository;
import com.thinkinnovative.library_management_system.security.JWTUtil;
import com.thinkinnovative.library_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody AuthRequestDTO request) {
        if(userRepository.findByMemberMemberID(request.getMemberId()) != null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "The user already registerd in database", null));
        }
        User response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(200, "The user registerd in database", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginRequestDTO request) {
        Optional<User> user = userService.findByUsername(request.getUsername());

        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(new ApiResponse<>(200,  "Token generated successfully",new LoginResponseDTO(token) ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401,  "Invalid credential", null  ));
    }
}
