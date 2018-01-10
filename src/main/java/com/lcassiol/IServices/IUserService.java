package com.lcassiol.IServices;

import com.lcassiol.entities.User;

import java.util.List;

public interface IUserService {

    public User findById(Long id);

    public User findUserByEmail(String email);

    public void saveUser(User user);

    public List<User> getAll();

    User updateUser(User user);
}
