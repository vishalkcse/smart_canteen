package com.smartcanteen.dao;

import com.smartcanteen.model.MenuItem;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> findAll();
    MenuItem findById(Long id);
    void save(MenuItem item);
}
