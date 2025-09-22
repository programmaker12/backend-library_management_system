package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.AuthRequestDTO;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import com.thinkinnovative.library_management_system.entity.User;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import com.thinkinnovative.library_management_system.repository.UserRepository;
import com.thinkinnovative.library_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final PasswordHashServiceImpl passwordHashService;


    public User registerUser(AuthRequestDTO request) {
        MemberTable  memberTable = memberRepository.findMemberEntityByID(request.getMemberId());
        User user = User.builder()
                .username(memberTable.getEmail())
                .password(passwordHashService.encodePassword(request.getPassword()))
                .email(memberTable.getEmail())
                .role(request.getRole())
                .member(memberTable).build();

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
