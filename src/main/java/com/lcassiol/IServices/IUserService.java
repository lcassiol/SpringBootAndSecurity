package com.lcassiol.IServices;

import com.lcassiol.entities.User;

public interface IUserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}
