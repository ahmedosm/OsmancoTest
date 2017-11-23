
package com.osmanco.springakka.mongo.repositories;

import com.osmanco.springakka.mongo.model.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface DomainRepository extends MongoRepository<Domain, Long> {

    Domain findFirstByDomainName(String domain);

    Domain findByDomainNameAndDisplayAds(String domain, boolean displayAds);

    //Supports native JSON query string
    @Query("{domainName:'?0'}")
    Domain findCustomByDomain(String domain);

    @Query("{domain: { $regex: ?0 } })")
    List<Domain> findCustomByRegExDomain(String domain);

}