package com.mpls.web2.service.utils;

import com.mpls.web2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if (ofNullable(userDetails = userRepository.findByPhone(s)).isPresent()) {
            return userDetails;
        } else if (ofNullable(userDetails = userRepository.findByEmail(s)).isPresent()) {
            return userDetails;
        }
        return null;
    }
}
