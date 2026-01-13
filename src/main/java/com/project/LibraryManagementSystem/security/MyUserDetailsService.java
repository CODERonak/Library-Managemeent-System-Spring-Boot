package com.project.LibraryManagementSystem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.model.entity.User;
import com.project.LibraryManagementSystem.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("email not found: " + email));

        return new MyUserDetails(user);
    }

}
