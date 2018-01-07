package com.lcassiol.services;

import com.lcassiol.IRepositories.IRoleRepository;
import com.lcassiol.IRepositories.IUserRepository;
import com.lcassiol.IServices.IUserService;
import com.lcassiol.entities.Role;
import com.lcassiol.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ROLE_BOOKS");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User userAfterUpdate) {
        User userBeforeUpdate = findUserByEmail(userAfterUpdate.getEmail());
        Set<Role> roles = userAfterUpdate.getRoles();
        userAfterUpdate.setRoles(userBeforeUpdate.getRoles());

        if(userAfterUpdate.getPassword() != null){
            userAfterUpdate.setPassword(userBeforeUpdate.getPassword());
        }

        if(roles != null){
            userAfterUpdate.getRoles().addAll(roles);
        }

        return userRepository.saveAndFlush(userAfterUpdate);
    }


}
