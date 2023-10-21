package com.rpggame.world.users.service;

import com.rpggame.world.users.model.User;
import com.rpggame.world.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public User findById(String userId) {
        return repository.findById(userId).orElse(null);
    }

    public void createUser(String userId, String startLocation) {
        repository.insert(User.createNew(userId, startLocation));
    }
}
