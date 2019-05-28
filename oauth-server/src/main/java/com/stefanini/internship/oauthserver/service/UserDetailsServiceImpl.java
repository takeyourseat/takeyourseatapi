package com.stefanini.internship.oauthserver.service;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(name);

        optionalUser.orElseThrow( () -> new UsernameNotFoundException("Username or password is wrong"));
        User userDetails = optionalUser.get();

        new AccountStatusUserDetailsChecker().check(userDetails);

        return new org.springframework.security.core.userdetails.User (
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.isEnabled(),
                userDetails.isAccountNonExpired(),
                userDetails.isCredentialsNonExpired(),
                userDetails.isAccountNonLocked(),
                userDetails.getAuthorities()
        );
    }
}
