/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osmanco.springakka.mongo.service;

import com.osmanco.springakka.mongo.model.Domain;
import com.osmanco.springakka.mongo.repositories.DomainRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahmed.anwer
 */
@Service
public class DomainServiceImpl implements DomainService{
    
    @Autowired
    DomainRepository domainRepository;
    @Override
    public void saveDomain(Domain domain) {
     domainRepository.save(domain);
    }
    @Override
    public void updateDomain(Domain domain) {
        domainRepository.save(domain);
    }

    @Override
    public void deleteDomain(Domain domain) {
       domainRepository.delete(domain);
    }
    @Override
    public List<Domain> getAllDomains() {
      return  domainRepository.findAll();
    }

    @Override
    public Domain getDomainByName(String domainName) {
        return domainRepository.findCustomByDomain(domainName);
    }
    
}
