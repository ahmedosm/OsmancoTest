package com.osmanco.springakka.cassandra.service;

import com.osmanco.springakka.common.util.PerfLog;
import com.osmanco.springakka.cassandra.model.Customer;
import com.osmanco.springakka.cassandra.repositories.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private CassandraOperations cassandraTemplate;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);        
       
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            cassandraTemplate.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    @PerfLog
    public List<Customer> getAllCustomers() {
        System.out.println("call getAllCustomers ");
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByName(String fname) {
        return customerRepository.findByFirstName(fname);
    }
}
