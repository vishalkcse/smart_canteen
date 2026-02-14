package com.smartcanteen.dao;

import com.smartcanteen.model.User;

public interface UserDao {
    User findByEmail(String email);
    void save(User user);
    User findById(Long id);
}
