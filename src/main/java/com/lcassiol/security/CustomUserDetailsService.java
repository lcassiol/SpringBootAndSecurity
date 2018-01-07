package com.lcassiol.security;

import com.lcassiol.IServices.IUserService;
import com.lcassiol.entities.Role;
import com.lcassiol.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        try {
            CustomUserDetails userDetails = searchUser(login);

            Collection<GrantedAuthority> permissoesPorUsuario = searchPermissions(login);

            Collection<GrantedAuthority> permissoesPorGrupo = new ArrayList<>();

            userDetails.getAuthorities().addAll(permissoesPorUsuario);
            userDetails.getAuthorities().addAll(permissoesPorGrupo);

            return userDetails;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Problemas com a tentativa de conexão!", e);
        }
    }

    public CustomUserDetails searchUser(String login) {
        User user = userService.findUserByEmail(login);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário " + login + " não encontrado!");
        }

        return new CustomUserDetails(user.getName(), login, user.getPassword(), user.getActive());
    }

    public Collection<GrantedAuthority> searchPermissions(String login){
        List<GrantedAuthority> permission = new ArrayList<>();
        User user = userService.findUserByEmail(login);

        if(user != null){
            Set<Role> roles = user.getRoles();

            for(Role role : roles){
                permission.add(new SimpleGrantedAuthority(role.getRole()));
            }

        }

        return permission;
    }

}