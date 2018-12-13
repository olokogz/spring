package org.traveloffice.spring.travelofficespring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.traveloffice.spring.travelofficespring.model.Customer;



public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

}
