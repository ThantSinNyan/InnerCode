package com.inner_code.service;

import com.inner_code.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    public User getUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("q2a6d@example.com");
        user.setPhone("123-456-7890");
        user.setAddress("123 Main St, Anytown, USA");
        return user;
    }
}
