package com.smartcanteen.service.impl;

import com.smartcanteen.dao.UserDao;
import com.smartcanteen.dao.impl.UserDaoImpl;
import com.smartcanteen.model.User;
import com.smartcanteen.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();

    @Override
    public User findByEmail(String email) { return dao.findByEmail(email); }

    @Override
    public void register(User user) { dao.save(user); }

    @Override
    public User findById(Long id) { return dao.findById(id); }
}
