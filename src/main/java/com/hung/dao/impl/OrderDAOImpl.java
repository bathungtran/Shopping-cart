package com.hung.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dao.OrderDAO;
import com.hung.dao.ProductDAO;
import com.hung.entity.Order;
import com.hung.entity.OrderDetail;
import com.hung.entity.Product;
import com.hung.model.CartInfo;
import com.hung.model.CartLineInfo;
import com.hung.model.CustomerInfo;
import com.hung.model.OrderDetailInfo;
import com.hung.model.OrderInfo;
import com.hung.model.PaginationResult;

@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;

	private int getMaxOrderNum() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "Select max(o.orderNum) from :order o";
		Query<?> query = session.createQuery(sql).setParameter("order", Order.class.getName());
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		return value;
	}

	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();

		int orderNum = this.getMaxOrderNum() + 1;
		Order order = new Order();

		order.setId(UUID.randomUUID().toString());
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(cartInfo.getAmountTotal());

		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerName(customerInfo.getName());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerPhone(customerInfo.getPhone());
		order.setCustomerAddress(customerInfo.getAddress());

		session.persist(order);

		List<CartLineInfo> lines = cartInfo.getCartLines();

		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setId(UUID.randomUUID().toString());
			detail.setOrder(order);
			detail.setAmount(line.getAmount());
			detail.setPrice(line.getProductInfo().getPrice());
			detail.setQuanity(line.getQuantity());

			String code = line.getProductInfo().getCode();
			Product product = this.productDAO.findProduct(code);
			detail.setProduct(product);

			session.persist(detail);
		}

		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(orderNum);

	}

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + OrderInfo.class.getName()//
				+ "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
				+ " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
				+ ":order ord "//
				+ " order by ord.orderNum desc";
		Session session = this.sessionFactory.getCurrentSession();

		Query<?> query = session.createQuery(sql).setParameter("order", Order.class.getName());
		return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
	}

	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(), //
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
	}
	
	public Order findOrder(String orderId) {
		return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

	@SuppressWarnings("unchecked")
	public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
		String sql = "Select new :orderDetailInfo" //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount)"//
                + " from :orderDetail d"//
                + " where d.order.id = :orderId";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query<OrderDetailInfo> query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
        query.setParameter("orderDetailInfo", OrderDetailInfo.class.getName());
        query.setParameter("orderDetail", OrderDetail.class.getName());
 
        return query.list();
    }
}
