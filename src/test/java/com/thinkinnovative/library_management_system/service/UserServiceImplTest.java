package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.AuthRequestDTO;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import com.thinkinnovative.library_management_system.entity.User;
import com.thinkinnovative.library_management_system.repository.MemberRepository;
import com.thinkinnovative.library_management_system.repository.UserRepository;

import com.thinkinnovative.library_management_system.service.serviceImpl.PasswordHashServiceImpl;
import com.thinkinnovative.library_management_system.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordHashServiceImpl passwordHashService;

    @InjectMocks
    private UserServiceImpl userService;

    private AuthRequestDTO request;
    private MemberTable member;
    private User user;

    @BeforeEach
    void setUp() {
        request = new AuthRequestDTO();
        request.setMemberId(1);
        request.setPassword("plainPassword");
        request.setRole(User.Role.valueOf("MEMBER"));

        member = new MemberTable();
        member.setMemberID(1);
        member.setEmail("test@example.com");

        user = User.builder()
                .username("test@example.com")
                .password("hashedPassword")
                .email("test@example.com")
                .role(User.Role.valueOf("MEMBER"))
                .member(member)
                .build();
    }

    @Test
    void testRegisterUser() {
        // Arrange
        when(memberRepository.findMemberEntityByID(1)).thenReturn(member);
        when(passwordHashService.encodePassword("plainPassword")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.registerUser(request);

        // Assert
        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getUsername());
        assertEquals("hashedPassword", savedUser.getPassword());
        assertEquals("MEMBER", savedUser.getRole());
        verify(memberRepository).findMemberEntityByID(1);
        verify(passwordHashService).encodePassword("plainPassword");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testFindByUsername() {
        // Arrange
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findByUsername("testUser");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getUsername());
        verify(userRepository).findByUsername("testUser");
    }
}
