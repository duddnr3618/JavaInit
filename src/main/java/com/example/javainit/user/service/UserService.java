package com.example.javainit.user.service;

import com.example.javainit.user.dto.UserDto;
import com.example.javainit.user.entity.Role;
import com.example.javainit.user.entity.UserEntity;
import com.example.javainit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveOrUpdate(UserDto userDto) {
        UserEntity userEntity = UserEntity.toUserEntity(userDto);

        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(Role.AMATEUR);
        userEntity.setCreateAt(LocalDate.now());

        userRepository.save(userEntity);
    }

    public void userRoleUpdate(UserDto userDto) {
        // userEmail로 UserEntity를 조회
        UserEntity userEntity = userRepository.findByUserEmail(userDto.getUserEmail());

        if (userEntity != null) {
            userEntity.setRole(Role.PRO);
            userRepository.save(userEntity);
        } else {
            throw new IllegalArgumentException("User not found with email: " + userDto.getUserEmail());
        }
    }


}
