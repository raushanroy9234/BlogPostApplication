package com.mountblue.blogApplication.BlogApplication.service;

import com.mountblue.blogApplication.BlogApplication.Repository.UserRepository;
import com.mountblue.blogApplication.BlogApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }
}
