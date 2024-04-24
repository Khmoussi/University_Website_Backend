package com.example.issatc.utilities;

import com.example.issatc.Infrastructure.JpaAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaAuthenticationRepository authenticationRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  authenticationRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
