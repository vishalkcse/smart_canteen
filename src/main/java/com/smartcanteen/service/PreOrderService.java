package com.smartcanteen.service;

import com.smartcanteen.model.PreOrder;
import java.util.List;

public interface PreOrderService {
    void placeOrder(PreOrder po);
    List<PreOrder> listByUser(Long userId);  // ✅ Used in servlet
    List<PreOrder> listAll();
}
