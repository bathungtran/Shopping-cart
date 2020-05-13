package com.hung.dao;

import java.util.List;

import com.hung.model.CartInfo;
import com.hung.model.OrderDetailInfo;
import com.hung.model.OrderInfo;
import com.hung.model.PaginationResult;

public interface OrderDAO {
	
	public void saveOrder(CartInfo cartInfo);
	 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
