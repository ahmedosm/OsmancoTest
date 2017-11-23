
package com.osmanco.springakka.cassandra.repositories;

import com.osmanco.springakka.cassandra.model.Customer;
import java.util.List;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends CassandraRepository<Customer> {

	@Query("Select * from customer where firstname=?0")
	public Customer findByFirstName(String firstName);

	@Query("Select * from customer where lastname=?0")
	public List<Customer> findByLastName(String lastName);

}