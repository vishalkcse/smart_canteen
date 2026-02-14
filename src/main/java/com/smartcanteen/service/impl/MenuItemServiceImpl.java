package com.smartcanteen.service.impl;

import com.smartcanteen.dao.MenuItemDao;
import com.smartcanteen.dao.impl.MenuItemDaoImpl;
import com.smartcanteen.model.MenuItem;
import com.smartcanteen.service.MenuItemService;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemDao dao = new MenuItemDaoImpl();
    @Override
    public List<MenuItem> listAll() { return dao.findAll(); }
    @Override
    public MenuItem findById(Long id) { return dao.findById(id); }
}
