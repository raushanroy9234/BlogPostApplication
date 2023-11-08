package com.mountblue.blogApplication.BlogApplication.service;

import com.mountblue.blogApplication.BlogApplication.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(User theUser);
}
