package com.customer.jdbc;

import com.customerenrichment.dto.Customer;


public interface CustomerDao {
	
	Customer getCustomer(String phone_number);
	
	
}
