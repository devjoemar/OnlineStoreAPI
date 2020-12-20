package com.devjoemar.OnlineStoreApi.service;

import com.devjoemar.OnlineStoreApi.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User save(User user);
}
