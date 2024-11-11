package com.example.javainit.user.userDetails;

import com.example.javainit.user.entity.UserEntity;
import com.example.javainit.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserEmail(userEmail);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
        return new CustomUserDetails(user.get());
    }

}
