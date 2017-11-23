package com.osmanco.springakka.mongo.service;

import com.osmanco.springakka.mongo.model.Domain;
import java.util.List;

/**
 *
 * @author ahmed.anwer
 */
public interface DomainService {

    void saveDomain(Domain domain);

    void updateDomain(Domain domain);

    void deleteDomain(Domain domain);

    List<Domain> getAllDomains();

    Domain getDomainByName(String domainName);
}
