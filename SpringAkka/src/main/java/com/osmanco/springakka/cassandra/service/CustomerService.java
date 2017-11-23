/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osmanco.springakka.cassandra.service;

import com.osmanco.springakka.cassandra.model.Customer;
import java.util.List;

/**
 *
 * @author ahmed.anwer
 */
public interface CustomerService {
    void saveCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerByName(String fname);
    
}
