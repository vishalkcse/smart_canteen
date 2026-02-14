package com.smartcanteen.service;

import com.smartcanteen.model.User;

public interface UserService {
    User findByEmail(String email);
    void register(User user);
    User findById(Long id);
}
