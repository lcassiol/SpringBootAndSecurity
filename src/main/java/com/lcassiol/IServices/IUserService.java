package com.lcassiol.IServices;

import com.lcassiol.entities.User;

import java.util.List;

public interface IUserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    public List<User> findAll();
}
