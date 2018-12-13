package org.traveloffice.spring.travelofficespring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.traveloffice.spring.travelofficespring.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address,Long> {
}
