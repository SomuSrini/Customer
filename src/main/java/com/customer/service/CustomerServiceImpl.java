package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.jdbc.CustomerDao;
import com.customerenrichment.dto.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao customerDao;
	
	public Customer getCustomer(String phone_number) {
		
		return customerDao.getCustomer(phone_number);
	}

}
