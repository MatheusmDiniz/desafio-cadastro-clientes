package com.desafio.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafio.domain.User;
import com.desafio.domain.enums.Profile;
import com.desafio.repositoties.UserRepository;
import com.desafio.security.UserSS;

import java.util.stream.Collectors;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        User obj = repo.findByUsuarioContaining(usuario);
        if (obj == null) {
            throw new UsernameNotFoundException(usuario);
        }
        return new UserSS(obj.getId(), obj.getUsuario(), obj.getSenha(), obj.getPerfis().stream().map(profile -> Profile.toEnum(profile)).collect(Collectors.toList()));
    }
}
