package com.padillatom.asadoremotobackend.service;

import com.padillatom.asadoremotobackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static final String LOGIN_EXCEPTION_MESSAGE = "No hay ninguna cuenta asociada con la dirección de correo electrónico.";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var foundUser = userRepository.findByAccountDetailsEmail(username);
        if (foundUser.isPresent()) {
            var authUser = foundUser.get();
            return new User(authUser.getAccountDetails().getEmail(),
                    authUser.getAccountDetails().getPassword(),
                    authUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
        } else {
            throw new UsernameNotFoundException(LOGIN_EXCEPTION_MESSAGE);
        }
    }
}
