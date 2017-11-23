package com.osmanco.springakka.controller;

import com.datastax.driver.core.utils.UUIDs;
import com.osmanco.springakka.cassandra.model.Customer;
import com.osmanco.springakka.cassandra.service.CustomerService;
import com.osmanco.springakka.dto.AddUserDTO;
import com.osmanco.springakka.dto.ResponseDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CassandraController {

    @Autowired
    CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CassandraController.class);

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> addCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            customer.setId(UUIDs.timeBased());
            customerService.saveCustomer(customer);
            responseDTO.setStatusCode("200");
            responseDTO.seteDescription("success");
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setStatusCode("500");
            responseDTO.seteDescription(e.getMessage());
        }
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> updateCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setStatusCode("0");
            responseDTO.seteDescription("success");
            Customer customerByName = customerService.getCustomerByName(customer.getFirstName());
            customerByName.setLastName(customer.getLastName());
            customerService.updateCustomer(customerByName);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setStatusCode("500");
            responseDTO.seteDescription("error");
        }
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers=null;
        try {
            customers = customerService.getAllCustomers();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

}
