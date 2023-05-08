package com.daniel.biblioteca_lpII.security;

import com.daniel.biblioteca_lpII.model.Cliente;
import com.daniel.biblioteca_lpII.repo.IClienteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//CLASE S2

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IClienteRepo repo;


    //VAMOSA CARGAR EL CLIENTE CON SU NOMBRE
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Cliente cliente = repo.findOneByNombreCompleto(nombre);

        if(cliente ==null)
            throw new UsernameNotFoundException("User not found");

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("CLIENT"));
        return new User(cliente.getNombreCompleto(),cliente.getPassword(),roles);
    }
}
