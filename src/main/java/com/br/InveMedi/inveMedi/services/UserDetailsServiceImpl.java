package com.br.InveMedi.inveMedi.services;

import com.br.InveMedi.inveMedi.models.User;
import com.br.InveMedi.inveMedi.repositories.UserRepository;
import com.br.InveMedi.inveMedi.security.UserSpringSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;



    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);
       if(Objects.isNull(user)){
           throw new UsernameNotFoundException("email nao encontrado" + email);
       }
       return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles() );

    }
}
