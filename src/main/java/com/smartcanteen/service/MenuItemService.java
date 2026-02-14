package com.smartcanteen.service;

import com.smartcanteen.model.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> listAll();
    MenuItem findById(Long id);
}
