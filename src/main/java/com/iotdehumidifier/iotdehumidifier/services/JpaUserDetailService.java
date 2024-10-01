package com.iotdehumidifier.iotdehumidifier.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iotdehumidifier.iotdehumidifier.models.UserDto;
import com.iotdehumidifier.iotdehumidifier.repositories.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;
    
    
    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userRepository.findByEmail(email)
            .map(UserDto::new)
            .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
    
}
