package com.devjoemar.OnlineStoreApi.service;

import com.devjoemar.OnlineStoreApi.exception.ResourceNotFoundException;
import com.devjoemar.OnlineStoreApi.model.User;
import com.devjoemar.OnlineStoreApi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
