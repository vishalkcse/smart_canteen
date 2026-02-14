package com.smartcanteen.dao;

import com.smartcanteen.model.PreOrder;

import java.util.List;

public interface PreOrderDao {
    void save(PreOrder po);
    List<PreOrder> findByUserId(Long userId);
    List<PreOrder> findAll();
}
