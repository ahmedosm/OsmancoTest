
package com.osmanco.springakka.controller;

import com.datastax.driver.core.utils.UUIDs;
import com.osmanco.springakka.cassandra.model.Customer;
import com.osmanco.springakka.dto.ResponseDTO;
import com.osmanco.springakka.mongo.model.Domain;
import com.osmanco.springakka.mongo.service.DomainService;
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
public class MongoController {
    
    @Autowired
    DomainService domainService;
    private final Logger logger = LoggerFactory.getLogger(MongoController.class);

    @RequestMapping(value = "/domain/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> addDomain(@RequestBody Domain domain, UriComponentsBuilder ucBuilder) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            domain.setId(UUIDs.timeBased());
            domainService.saveDomain(domain);
            responseDTO.setStatusCode("200");
            responseDTO.seteDescription("success");
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setStatusCode("500");
            responseDTO.seteDescription(e.getMessage());
        }
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/domain/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> updateDomain(@RequestBody Domain domain, UriComponentsBuilder ucBuilder) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Domain domainByName = domainService.getDomainByName(domain.getDomainName());
            domainByName.setDisplayAds(domain.isDisplayAds());
            domainService.updateDomain(domainByName);
            responseDTO.setStatusCode("0");
            responseDTO.seteDescription("success");
           
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setStatusCode("500");
            responseDTO.seteDescription("error");
        }
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/domain/getAllDomains", method = RequestMethod.GET)
    public ResponseEntity<List<Domain>> getAllDomains() {
        List<Domain> domains=null;
        try {
         domains=domainService.getAllDomains();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Domain>>(domains, HttpStatus.OK);
    }
}
