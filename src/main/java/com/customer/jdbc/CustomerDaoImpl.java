package com.customer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.customerenrichment.dto.Address;
import com.customerenrichment.dto.Customer;



@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String query = "select a.cust_id,a.first_name,"
			+ "a.last_name,a.phone_number,a.email,b.address1,"
			+ "b.address2,b.city,b.state,b.country from "
			+ "enrichment.customer a,enrichment.address b "
			+ "where a.cust_id = b.cust_id and a.phone_number=?;";

	public Customer getCustomer(String phone_number) {
		
		List<Customer> customerList =  jdbcTemplate.query(query, new Object[]{phone_number}, new RowMapper<Customer>(){

			public Customer mapRow(ResultSet result, int arg1)
					throws SQLException {
				Address address = new Address();
				address.setAddressLine1(result.getString("ADDRESS1"));
				address.setAddressLine2(result.getString("ADDRESS2"));
				address.setCity(result.getString("CITY"));
				address.setState(result.getString("STATE"));
				address.setCountry(result.getString("COUNTRY"));
				Customer customer = new Customer();
				customer.setCust_id(result.getInt("CUST_ID"));
				customer.setFname(result.getString("FIRST_NAME"));
				customer.setLname(result.getString("LAST_NAME"));
				customer.setEmail(result.getString("EMAIL"));
				customer.setAddress(address);
				return customer;
			}
			
		});
		if (customerList.isEmpty()) {
			return null;
		} else if (customerList.size() == 1) {
			return customerList.get(0);
		}
		
		return null;
	}

}
