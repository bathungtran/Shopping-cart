package com.hung.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dao.AccountDAO;
import com.hung.entity.Account;

@Transactional
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Account findAccount(String userName) {
		return sessionFactory.getCurrentSession().get(Account.class, userName);
	}

}
