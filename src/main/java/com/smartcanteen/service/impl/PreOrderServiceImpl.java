package com.smartcanteen.service.impl;

import com.smartcanteen.dao.PreOrderDao;
import com.smartcanteen.dao.impl.PreOrderDaoImpl;
import com.smartcanteen.model.PreOrder;
import com.smartcanteen.service.PreOrderService;

import java.util.List;

public class PreOrderServiceImpl implements PreOrderService {
    private final PreOrderDao dao = new PreOrderDaoImpl();

    @Override
    public void placeOrder(PreOrder po) {
        dao.save(po);
    }

    @Override
    public List<PreOrder> listByUser(Long userId) {
        return dao.findByUserId(userId);  // ✅ This must be implemented in your DAO
    }

    @Override
    public List<PreOrder> listAll() {
        return dao.findAll();
    }
}
